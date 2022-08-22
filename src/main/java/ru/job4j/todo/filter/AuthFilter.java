package ru.job4j.todo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        if (uri.endsWith("loginPage") || uri.endsWith("login")
                || uri.endsWith("formRegistration") || uri.endsWith("success")
                || uri.endsWith("fail") || uri.endsWith("registration") || uri.endsWith("index")) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("account") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        chain.doFilter(req, res);
    }
}