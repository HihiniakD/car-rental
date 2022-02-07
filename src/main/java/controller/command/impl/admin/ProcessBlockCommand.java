package controller.command.impl.admin;

import controller.command.Command;
import model.service.UserService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static controller.Constants.*;
import static controller.Path.*;

public class ProcessBlockCommand implements Command {

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isBlocked = Boolean.parseBoolean(request.getParameter(BLOCKED_PARAMETER));
        int userId = Integer.parseInt(request.getParameter(ID_PARAMETER));

        if (userService.changeBlockedStatus(userId, isBlocked))
            request.getSession().setAttribute(MESSAGE_PARAMETER, SUCCESS_MESSAGE);
        else
            request.getSession().setAttribute(MESSAGE_PARAMETER, FAIL_MESSAGE);

        return REDIRECT + USERS_PAGE_COMMAND;
    }
}
