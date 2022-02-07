package controller.filter;

import controller.Path;
import model.entity.User;
import model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static controller.Constants.*;

@WebFilter(filterName = "AccessFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "guest_urls", value =
        "/,/login,/process_login,/sign_up,/search_cars,/process_sign_up,/search_cars,/search_cars/view_deal,/sortByName," +
                "/sortByPrice"),
        @WebInitParam(name = "user_urls", value = "/log_out,/my_booking,/info,/search_cars/view_deal," +
                "/search_cars/view_deal/book,/search_cars/view_deal/book/process_booking"),
        @WebInitParam(name = "admin_urls", value = "/admin_page,/log_out,/users,/cars,/managers,/blocking,/addManager," +
                "/processAddManager,/deleteCar,/editCar,/processEditCar,/addCar,/processAddCar"),
        @WebInitParam(name = "manager_urls", value = "/manager_page,/log_out,/approveBooking,/declineBooking,/finishBooking," +
                "/processDeclineBooking,/processFinishBooking")})
public class AccessFilter implements Filter {

    public static final String USER_ATTRIBUTE = "user";
    public static final String GUEST_URLS = "guest_urls";
    public static final String USER_URLS = "user_urls";
    public static final String MANAGER_URLS = "manager_urls";
    public static final String ADMIN_URLS = "admin_urls";

    private List<String> guestUrls = new ArrayList<>();
    private List<String> userUrls = new ArrayList<>();
    private List<String> adminUrls = new ArrayList<>();
    private List<String> managerUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String guestParameter = filterConfig.getInitParameter(GUEST_URLS);
        String userParameter = filterConfig.getInitParameter(USER_URLS);
        String adminParameter = filterConfig.getInitParameter(ADMIN_URLS);
        String managerParameter = filterConfig.getInitParameter(MANAGER_URLS);

        guestUrls = Arrays.stream(guestParameter.split(",")).map(String::trim).collect(Collectors.toList());
        userUrls = Arrays.stream(userParameter.split(",")).map(String::trim).collect(Collectors.toList());
        adminUrls = Arrays.stream(adminParameter.split(",")).map(String::trim).collect(Collectors.toList());
        managerUrls = Arrays.stream(managerParameter.split(",")).map(String::trim).collect(Collectors.toList());

        //залогировать все созданные урлы
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);
        System.out.println(request.getRequestURI());

        if (guestUrls.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        if (userUrls.contains(request.getRequestURI()) && session.getAttribute(USER_ATTRIBUTE) == null) {
            session.setAttribute(ERROR_PARAMETER, MUST_LOGIN);
            response.sendRedirect(Path.LOGIN_PATH);
            return;
        }
        if (session != null && session.getAttribute(USER_ATTRIBUTE) != null) {
            User user = (User) session.getAttribute(USER_ATTRIBUTE);
            if (Role.ADMIN.getRole() == user.getRoleId()) {
                if (adminUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            } else if (Role.USER.getRole() == user.getRoleId()) {
                if (userUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            } else if (Role.MANAGER.getRole() == user.getRoleId()) {
                if (managerUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }

            }
            request.getRequestDispatcher(Path.NOT_FOUND_VIEW).forward(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
