package model.dao;

import model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{
    boolean changeBlockedStatus(int userId, boolean blocked);
    int getNumberOfUsers();
    User findUserByEmail(String email);
    boolean changeUserNameById(int id, String name);
    List<User> findUsers(int start, int end);
    List<User> findAllManagers();
}
