package controller.command.impl.admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.Path.ADD_MANAGER_VIEW;

public class AddManagerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ADD_MANAGER_VIEW;
    }
}
