package ru.tututu.trains.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private Long userId;
    private String login;
    @JsonIgnore private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long userId, String login, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    public UserPrincipal(Long userId, String login, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.login = login;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
