package com.example.hospital.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.hospital.dtos.UsuarioDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager manager) {
        super(manager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioDto loginDto = new ObjectMapper().readValue(request.getInputStream(), UsuarioDto.class);
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return null;
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        Date dateNow = new Date();

        List<String> roles = authResult.getAuthorities()
                .stream().map(a -> a.getAuthority().substring(5))
                .collect(Collectors.toList());

        String token = JWT.create()
                .withIssuer("hexdump95")
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(dateNow.getTime() + 3600 * 1000))
                .withClaim("rol", roles)
                .sign(Algorithm.HMAC256("secret"));

        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }


}
