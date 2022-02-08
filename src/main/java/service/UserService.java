package service;

import model.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> signUpUser(String name, String password, String email, String phone);
    User loginUser(String email, String password);
    User checkUsernameChange(HttpSession session, String name);
    int getNumberOfUsers();
    List<User> findUsersPagination(int currentPage, int rowsPerPage);
    boolean changeBlockedStatus(int userId, boolean blocked);
    List<User> findAllManagers();
    Optional<User> createManager(String name, String password, String email, String phone);

}
