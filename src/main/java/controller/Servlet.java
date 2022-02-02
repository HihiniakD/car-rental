package controller;

import controller.command.Command;
import controller.command.factory.CommandFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Path.*;

@WebServlet(value = "/")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/", "");
        Command command = CommandFactory.getCommand(path);
        String page = command.execute(request, response);
        if (page.contains(BACK_TO_INDEX)){
            command = CommandFactory.getCommand(EMPTY_COMMAND);
            page = command.execute(request, response);
        }
        if(page.contains(REDIRECT)){
            response.sendRedirect(request.getContextPath() + page.replace(REDIRECT, MAIN_PATH));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
