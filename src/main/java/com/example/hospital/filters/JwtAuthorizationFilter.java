package com.example.hospital.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (request.getHeader(HttpHeaders.AUTHORIZATION).matches("Bearer a.a.a")) {

                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken("f9537096-fd5a-4b98-b100-02ba4c8e266e", null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(authRequest);

                chain.doFilter(request, response);
            }
        }catch(Exception err) {
            chain.doFilter(request, response);
        }

    }

}