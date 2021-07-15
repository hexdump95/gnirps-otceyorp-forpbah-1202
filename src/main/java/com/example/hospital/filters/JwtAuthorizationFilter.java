package com.example.hospital.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.hospital.services.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtService jwtService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ") && authHeader.split("\\.").length == 3) {

            try {
                DecodedJWT decodedJWT = jwtService.decodeJwt(authHeader);

                List<GrantedAuthority> authorities =
                        jwtService.getDecodedAuthorities(decodedJWT);

                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(),
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(authRequest);

                boolean isJwtCloseToExpire = jwtService.isJwtCloseToExpire(decodedJWT);
                if (isJwtCloseToExpire){
                    response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.updateJwt(decodedJWT));
                }
            } catch (Exception err) {
                return;
            }
        }

        chain.doFilter(request, response);
    }

}
