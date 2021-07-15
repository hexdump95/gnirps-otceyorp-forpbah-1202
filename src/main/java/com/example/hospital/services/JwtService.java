package com.example.hospital.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtService {
    public String createToken(Authentication auth){
        Date dateNow = new Date();

        List<String> roles = auth.getAuthorities()
                .stream().map(a -> a.getAuthority().substring(5))
                .collect(Collectors.toList());

        return JWT.create()
                .withIssuer("hexdump95")
                .withSubject(auth.getName())
                .withExpiresAt(new Date(dateNow.getTime() + 10 * 60 * 1000))
                .withClaim("rol", roles)
                .sign(Algorithm.HMAC256("secret"));
    }

    public DecodedJWT decodeJwt(String authHeader) {
        String jwt = authHeader.substring(7);
        return JWT.require(Algorithm.HMAC256("secret"))
                .build().verify(jwt);
    }

    public List<GrantedAuthority> getDecodedAuthorities(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("rol").asList(String.class)
                .stream()
                .map(r -> "ROLE_" + r)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<String> getDecodedRoles(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("rol").asList(String.class);
    }

    public String updateJwt(DecodedJWT decodedJWT) {
        Date dateNow = new Date();
        List<String> roles = this.getDecodedRoles(decodedJWT);
        return JWT.create()
                .withIssuer("hexdump95")
                .withSubject(decodedJWT.getSubject())
                .withExpiresAt(new Date(dateNow.getTime() + 10 * 60 * 1000))
                .withClaim("rol", roles)
                .sign(Algorithm.HMAC256("secret"));
    }

    public boolean isJwtCloseToExpire(DecodedJWT decodedJWT) {
        Long dateNow = new Date().getTime();
        Long jwtExpiresAt = decodedJWT.getExpiresAt().getTime();
        return jwtExpiresAt - dateNow < 5 * 60 * 1000; // 5 minutes
    }
}
