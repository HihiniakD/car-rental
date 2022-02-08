package controller.command.impl.admin;

import controller.command.Command;
import model.entity.User;
import service.UserService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static controller.Constants.*;
import static controller.Path.*;

public class ManagersCommand implements Command {

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> managers = userService.findAllManagers();
        request.setAttribute(MANAGERS_PARAMETER, managers);

        return MANAGERS_VIEW;
    }
}
