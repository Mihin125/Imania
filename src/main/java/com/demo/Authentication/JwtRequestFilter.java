package com.demo.Authentication;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sun.plugin.dom.exception.InvalidAccessException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private ImaniaUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    String jwtToken = null;
    String username = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("AuthorizationJwt");

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            //Seperate the token
            jwtToken = requestTokenHeader.substring(7);
            if(!jwtUserDetailsService.isLoggedOut(jwtToken))

                throw new InvalidAccessException("Please Login to continue");
            try {
                username = jwtUtil.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }   else if(requestTokenHeader == null)logger.warn("JWT Token is Empty");
        else { logger.warn("JWT Token does not begin with Bearer String"); }

        //validate the token.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set authentication
            if (jwtUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else System.out.println("not validated");
        }else System.out.println("something is wrong");
        chain.doFilter(request, response);
    }

    public void LogOutToken(){
        LogOutList logOutList =new LogOutList();
        logOutList.setLoggedOutToken(jwtToken);
        logOutList.setUsername(username);
        jwtUserDetailsService.deleteLoggedOutTokenByUsername(username);
        jwtUserDetailsService.addLogOutToken(logOutList);
        System.out.println(jwtToken);
    }


}
