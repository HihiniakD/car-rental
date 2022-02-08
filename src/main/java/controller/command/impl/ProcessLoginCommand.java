package controller.command.impl;

import controller.command.Command;
import controller.utils.RequestUtils;
import model.entity.User;
import model.entity.enums.Role;
import org.apache.log4j.Logger;
import service.exception.HashException;
import service.exception.ServiceException;
import service.UserService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Path.*;

public class ProcessLoginCommand implements Command {

    private static final Logger logger = Logger.getLogger(ProcessLoginCommand.class);

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (RequestUtils.getSessionAttribute(request, USER_PARAMETER, User.class) != null) {
            // if we somehow opened /login page while being already logged in, we just do redirect to catalog (/)
            return REDIRECT;
        }

        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        User user = null;
        try {
            user = userService.loginUser(email, password);
        }catch (ServiceException | HashException exception){
            request.setAttribute(ERROR_PARAMETER, exception.getMessage());
            return LOGIN_VIEW;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_PARAMETER, user);
        session.setMaxInactiveInterval(1800);
        logger.info(String.format("%s with role %s successfully logged in", user.getName(), user.getRoleId()));

        if (Role.ADMIN.getRole() == user.getRoleId())
            return REDIRECT + ADMIN_PAGE_COMMAND;

        if (Role.MANAGER.getRole() == user.getRoleId())
            return REDIRECT + MANAGER_PAGE_COMMAND;

        return REDIRECT + MY_BOOKING_COMMAND;
    }
}
