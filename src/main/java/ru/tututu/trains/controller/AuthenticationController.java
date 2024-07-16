package ru.tututu.trains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tututu.trains.model.LoginRequestModel;
import ru.tututu.trains.model.LoginResponseModel;
import ru.tututu.trains.security.JWTIssuer;
import ru.tututu.trains.security.UserPrincipal;

@RestController
public class AuthenticationController {
    @Autowired
    private final JWTIssuer jwtIssuer;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(JWTIssuer jwtIssuer, AuthenticationManager authenticationManager) {
        this.jwtIssuer = jwtIssuer;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public LoginResponseModel login(@RequestBody @Validated LoginRequestModel request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = jwtIssuer.issue(principal.getUserId(), principal.getUsername(), roles);
        return new LoginResponseModel(token);
    }
}
