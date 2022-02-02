package controller.command.impl;

import controller.command.Command;
import controller.utils.RequestUtils;
import model.entity.User;
import model.entity.enums.Role;
import model.exception.HashException;
import model.exception.ServiceException;
import model.service.UserService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Path.*;

public class ProcessLoginCommand implements Command {

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

        if (Role.ADMIN.getRole() == user.getRoleId())
            return REDIRECT + "admin_page";
        return REDIRECT;
    }
}
