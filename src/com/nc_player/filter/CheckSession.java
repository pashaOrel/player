package com.nc_player.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by INDIGO-ПС on 02.04.2016.
 */
public class CheckSession implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(session.getAttribute("session")!=null && session.getAttribute("id")!=null){
            filterChain.doFilter(servletRequest, servletResponse);
          //  request.getRequestDispatcher("JSPfiles/player_page.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("intro_page.jsp").forward(request, response);
        }
    }

    public void destroy() {

    }
}
