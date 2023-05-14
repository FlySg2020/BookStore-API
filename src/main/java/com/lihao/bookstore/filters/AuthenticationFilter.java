package com.lihao.bookstore.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lihao.bookstore.services.UserService;
import com.lihao.bookstore.utils.TokenUtils;

public class AuthenticationFilter extends OncePerRequestFilter{

    @Value("${auth.header}")
    private String TOKEN_HEADER;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        final String header = request.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if(header != null && securityContext.getAuthentication() == null) {
            String token = header.substring("bearer ".length());
            String username = tokenUtil.getUsernameFromToken(token);
            if(username != null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                if(tokenUtil.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
