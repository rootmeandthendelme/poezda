package ru.tututu.trains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.User;
import ru.tututu.trains.repo.UserRepo;
import ru.tututu.trains.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Component
public class UsersDetailsService implements UserDetailsService {
    @Autowired
    private final UserService userService;

    public UsersDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
            User user = userService.findByLogin(username);

            return new UserPrincipal(
                    (long) user.getId(),
                    user.getLogin(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole()))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
