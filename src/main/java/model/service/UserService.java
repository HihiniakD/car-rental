package model.service;

import model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> signUpUser(String name, String password, String email, String phone);
    User loginUser(String email, String password);

}
