package controller.command.impl.admin;

import controller.command.Command;
import model.entity.User;
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
import static controller.Constants.PHONE_PARAMETER;
import static controller.Path.*;
import static controller.Path.NOT_FOUND_VIEW;

public class ProcessAddManagerCommand implements Command {

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(EMAIL_PARAMETER).toLowerCase().trim();
        String password = request.getParameter(PASSWORD_PARAMETER);
        String name = request.getParameter(NAME_PARAMETER);
        String phone = request.getParameter(PHONE_PARAMETER);
        Optional<User> user = null;
        try {
            user = userService.createManager(name, password, email, phone);
        } catch (ServiceException exception) {
            request.setAttribute(ERROR_PARAMETER, exception.getMessage());
            request.setAttribute(EMAIL_PARAMETER, email);
            request.setAttribute(NAME_PARAMETER, name);
            request.setAttribute(PHONE_PARAMETER, phone);
            return ADD_MANAGER_VIEW;
        } catch (HashException exception){
            request.setAttribute(ERROR_PARAMETER, OTHER_ERROR);
            return ADD_MANAGER_VIEW;
        }

        if (user.isPresent()){
            HttpSession session = request.getSession(true);
            session.setAttribute(SUCCESS_MESSAGE_PARAMETER, SUCCESS_MESSAGE);
            return REDIRECT + MANAGERS_PAGE_COMMAND;
        }
        return NOT_FOUND_VIEW;
    }
}
