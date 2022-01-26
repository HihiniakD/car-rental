package model.service;

import model.entity.User;

import java.util.Optional;

public interface UserService {
    Boolean signUpUser(String name, String password, String email, String phone);
    Optional<User> loginUser(String email);

}
