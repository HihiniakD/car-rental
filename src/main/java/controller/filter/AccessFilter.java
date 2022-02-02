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

@WebFilter(filterName = "AccessFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "guest_urls", value =
        "/,/login,/process_login,/sign_up,/search_cars,/process_sign_up,/search_cars"),
        @WebInitParam(name = "user_urls", value = "/log_out,/info,/search_cars/book_car," +
                "/search_cars/book_car/confirm_booking,/cancel_order"),
        @WebInitParam(name = "admin_urls", value = "/admin_page,/add_car,/car_list,/search_by_user")})
public class AccessFilter implements Filter {

    private List<String> guestUrls = new ArrayList<>();
    private List<String> userUrls = new ArrayList<>();
    private List<String> adminUrls = new ArrayList<>();
    public static final String USER_ATTRIBUTE = "user";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String guestParameter = filterConfig.getInitParameter("guest_urls");
        String userParameter = filterConfig.getInitParameter("user_urls");
        String adminParameter = filterConfig.getInitParameter("admin_urls");

        guestUrls = Arrays.stream(guestParameter.split(",")).map(String::trim).collect(Collectors.toList());
        userUrls = Arrays.stream(userParameter.split(",")).map(String::trim).collect(Collectors.toList());
        adminUrls = Arrays.stream(adminParameter.split(",")).map(String::trim).collect(Collectors.toList());

        System.out.println(guestUrls + "\n" + userUrls + "\n" + adminUrls);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);
        System.out.println(request.getRequestURI());
        System.out.println(guestUrls.contains(request.getRequestURI()) + " IF CONTAINS");

        if (guestUrls.contains(request.getRequestURI())){
            filterChain.doFilter(request, response);
            return;
        }
        if (userUrls.contains(request.getRequestURI()) && session.getAttribute(USER_ATTRIBUTE) == null){
            response.sendRedirect(Path.LOGIN_PATH);
            return;
        }
        if (session != null && session.getAttribute(USER_ATTRIBUTE) != null){
            User user = (User) session.getAttribute(USER_ATTRIBUTE);
            if (Role.ADMIN.getRole() == user.getRoleId()){
                if (adminUrls.contains(request.getRequestURI())){
                    filterChain.doFilter(request, response);
                    return;
                }
            } else if (Role.USER.getRole() == user.getRoleId()) {
                if (userUrls.contains(request.getRequestURI())){
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        System.out.println(session.getAttribute(USER_ATTRIBUTE) + " USER ATTRIBUTE ACCESS");
        request.getRequestDispatcher(Path.NOT_FOUND_VIEW).forward(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
