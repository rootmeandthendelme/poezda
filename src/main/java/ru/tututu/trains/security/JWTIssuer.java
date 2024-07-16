package ru.tututu.trains.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class JWTIssuer {
    private final JWTProperties jwtProperties;

    public JWTIssuer(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String issue(long userId, String login, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("l", login)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }

    public String issue(long userId, String login, List<String> roles, String secret){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("l", login)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(secret));
    }
}
