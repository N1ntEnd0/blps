package com.example.lab1.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.lab1.service.ChangeOrgUserDetailsService;
import com.example.lab1.utils.JWTutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

//@WebFilter({"/petition/add","/petition/subscribe"})
public class JwtFilter implements Filter{

    @Autowired
    private JWTutils jwTutils;
    @Autowired
    private ChangeOrgUserDetailsService changeOrgUserDetailsService;
//    Logger logger = LogManager.getLogger(JwtFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwTutils.getTokenFromRequest((HttpServletRequest) servletRequest);
        if(token != null && jwTutils.validateToken(token)){
//            logger.log(Level.INFO, "Filter logs: token exists");
            String email = jwTutils.getLoginFromToken(token);
            try{
                UserDetails user = changeOrgUserDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
//                logger.log(Level.INFO, "Filter logs: auth completed");
            }catch (NullPointerException e){
//                logger.log(Level.INFO, "Filter logs: auth failed");
                ((HttpServletResponse)servletResponse).setStatus(401);
                ((HttpServletResponse)servletResponse).sendError(401, "Authorization failed. Invalid token");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
