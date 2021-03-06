package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * I18n filter for every page
 */
@WebFilter(filterName = "LangFilter", urlPatterns = "/*")
public class LangFilter implements Filter {

    public static final String EN_LANG = "en";
    public static final String UK_LANG = "ua";
    public static final String LANG_COOKIE_NAME = "lang";
    public static final String ENCODING = "UTF-8";

    /**
     * Retrieving lang cookie and sets appropriate language for every request
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        response.setCharacterEncoding(ENCODING);
        HttpServletRequest req = (HttpServletRequest) request;
        String lang = getCookieValue(req);
        if (lang != null && (lang.equals(EN_LANG) || lang.equals(UK_LANG)))
            request.setAttribute(LANG_COOKIE_NAME, lang);
        else
            request.setAttribute(LANG_COOKIE_NAME, UK_LANG);
        filterChain.doFilter(request, response);
    }

    private String getCookieValue(HttpServletRequest request) {
        if(request != null) {
            Cookie[] cookies = request.getCookies();
            if(cookies != null)
                for (Cookie cookie : cookies) {
                    if (LANG_COOKIE_NAME.equalsIgnoreCase(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
        }
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
