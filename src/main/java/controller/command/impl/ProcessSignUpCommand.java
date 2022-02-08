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
import java.util.Optional;

import static controller.Constants.*;
import static controller.Path.*;


public class ProcessSignUpCommand implements Command {

    private static final Logger logger = Logger.getLogger(ProcessSignUpCommand.class);

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (RequestUtils.getSessionAttribute(request, USER_PARAMETER, User.class) != null) {
            // if we somehow opened /login page while being already logged in, we just do redirect to catalog (/)
            return REDIRECT;
        }

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
            return SIGNUP_VIEW;
        }

        if (user.isPresent()){
            HttpSession session = request.getSession(true); // загуглить
            session.setAttribute(USER_PARAMETER, user.get());
            session.setMaxInactiveInterval(86400);
            logger.info(String.format("%s successfully signed up", user.get().getName()));
            if (Role.ADMIN.getRole() == user.get().getRoleId()) {
                return REDIRECT + ADMIN_PAGE_COMMAND;
            }
            session.setAttribute(SUCCESS_MESSAGE_PARAMETER, SUCCESS_SIGN_UP_MESSAGE);
            return REDIRECT + MY_BOOKING_COMMAND;
        }
        return NOT_FOUND_VIEW;
        }


}
