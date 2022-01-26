package model.service.impl;

import model.entity.User;
import model.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public Boolean signUpUser(String name, String password, String email, String phone) {
        return null;
    }

    @Override
    public Optional<User> loginUser(String email) {
        return Optional.empty();
    }
}
