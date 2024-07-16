package ru.tututu.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.User;
import ru.tututu.trains.repo.UserRepo;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findByLogin(String login) throws SQLException {
        return Optional.of(userRepo.findByLogin(login));
    }
}
