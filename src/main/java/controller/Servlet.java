package controller;

import controller.command.Command;
import controller.command.factory.CommandFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


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
        System.out.println(path);
        path = path.replaceAll(".*/", "");
        Command command = CommandFactory.getCommand(path);
        String page = command.execute(request, response);
        if(page.contains("redirect")){
            response.sendRedirect(request.getContextPath() + page.replace("redirect", "/"));
        } else {
            System.out.println(page);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
