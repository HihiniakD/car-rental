package controller.command.impl;

import controller.command.Command;
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
            return "redirect" + "admin_page";
        return "WEB-INF/views/infoView.jsp";
    }
}
