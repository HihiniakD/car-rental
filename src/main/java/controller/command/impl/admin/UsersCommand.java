package controller.command.impl.admin;

import controller.command.Command;
import model.entity.User;
import model.service.UserService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static controller.Constants.*;
import static controller.Path.USERS_VIEW;

public class UsersCommand implements Command {

    private final int recordsPerPage = 5;
    private int currentPage = 1;
    private int numberOfPages;

    private final UserService userService =  ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getCurrentPage(request);
        int numberOfRows = userService.getNumberOfUsers();

        List<User> users = userService.findUsersPagination(currentPage, numberOfPages);
        getNumberOfPages(numberOfRows);

        request.setAttribute(USERS_PARAMETER, users);
        request.setAttribute(CURRENT_PAGE_PARAMETER, currentPage);
        request.setAttribute(NUMBER_OF_PAGES_PARAMETER, numberOfPages);

        return USERS_VIEW;
    }

    private void getCurrentPage(HttpServletRequest request) {
        Optional<String> page = Optional.ofNullable(request.getParameter("currentPage"));
        currentPage = page.map(Integer::valueOf).orElse(1);
    }

    private void getNumberOfPages(int numberOfRows) {
        numberOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            numberOfPages++;
        }
    }
}
