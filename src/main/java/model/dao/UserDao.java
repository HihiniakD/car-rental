package model.dao;

import model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{

    User findUserByEmail(String email);
    List<User> findAllUsers();
    boolean changeUserNameById(int id, String name);
}
