package ru.tututu.trains.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JWTToPrincipalConverter {
    public UserPrincipal convert(DecodedJWT jwt){
        return new UserPrincipal(
                Long.valueOf(jwt.getSubject()),
                jwt.getClaim("l").asString(),
                extractAuthorities(jwt)
        );
    }

    private List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT jwt){
        var claim = jwt.getClaim("a");
        if(claim.isNull() || claim.isMissing())
            return List.of();

        return claim.asList(SimpleGrantedAuthority.class);
    }
}
