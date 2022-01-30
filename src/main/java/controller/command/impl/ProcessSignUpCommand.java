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
import java.util.Optional;

import static controller.Constants.*;
import static controller.Path.SIGNUP_VIEW;


public class ProcessSignUpCommand implements Command {

    UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(EMAIL_PARAMETER).toLowerCase().trim();
        String password = request.getParameter(PASSWORD_PARAMETER);
        String name = request.getParameter(NAME_PARAMETER);
        String phone = request.getParameter(PHONE_PARAMETER);
        Optional<User> user = null;
        try {
            user = userService.signUpUser(name, password, email, phone);
        } catch (ServiceException exception) {
            request.setAttribute(ERROR_PARAMETER, exception.getMessage());
            request.setAttribute(EMAIL_PARAMETER, email);
            request.setAttribute(NAME_PARAMETER, name);
            request.setAttribute(PHONE_PARAMETER, phone);
            return SIGNUP_VIEW;
        } catch (HashException exception){
            request.setAttribute(ERROR_PARAMETER, OTHER_ERROR);
        }

        if (user.isPresent()){
            HttpSession session = request.getSession(true); // загуглить
            session.setAttribute(USER_PARAMETER, user.get());
            session.setMaxInactiveInterval(86400);
            if (Role.ADMIN.getRole() == user.get().getRoleId()) {
                return "redirect" + "admin_page";
            }
            return "WEB-INF/views/infoView.jsp";
        }
        return "WEB-INF/views/error.jsp";
        }


}
