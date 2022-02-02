package controller.command.impl;

import controller.command.Command;
import controller.utils.RequestUtils;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.Constants.USER_PARAMETER;
import static controller.Path.*;

public class SignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (RequestUtils.getSessionAttribute(request, USER_PARAMETER, User.class) != null) {
            // if we somehow opened /login page while being already logged in, we just do redirect to catalog (/)
            return REDIRECT;
        }
        return SIGNUP_VIEW;
    }
}
