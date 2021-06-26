package com.example.hospital.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer ") && authHeader.split("\\.").length == 3) {
                String jwt = authHeader.substring(7);
                System.out.println(jwt);

                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret"))
                        .build().verify(jwt);

                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(authRequest);

                chain.doFilter(request, response);
            }
        } catch (Exception err) {
            chain.doFilter(request, response);
        }

    }

}