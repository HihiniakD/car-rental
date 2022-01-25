package model.dao;

import model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{
    List<User> findAllUsers();
}
