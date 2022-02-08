package service.impl;

import controller.security.Security;
import dao.UserDao;
import dao.impl.factory.JDBCDaoFactory;
import model.entity.User;
import model.entity.enums.Role;
import service.exception.ServiceException;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static controller.Constants.*;

/**
 * Implementation of UserService
 */
public class UserServiceImpl implements UserService {

     private final UserDao userDao = JDBCDaoFactory.getInstance().createUserDao();

    @Override
    public Optional<User> signUpUser(String name, String password, String email, String phone) throws ServiceException {
        validateSignUpParams(name, password, email, phone);
        String hashPassword = Security.hashPassword(password);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashPassword);
        user.setPhone(phone);
        user.setRoleId(Role.USER.getRole());
        user.setBlocked(false);
        userDao.create(user);
        return Optional.ofNullable(userDao.findUserByEmail(email));
    }

    @Override
    public User loginUser(String email, String password) throws ServiceException{
        validateLoginParams(email, password);
        User user = userDao.findUserByEmail(email);

        if (user == null)
            throw new ServiceException(USER_NOT_FOUND);

        try {
            if (!Security.isPasswordCorrect(password, user.getPassword()))
                throw new ServiceException(WRONG_PASSWORD);
        } catch (Exception e) {
            throw new ServiceException(WRONG_PASSWORD);
        }

        if (user.isBlocked())
            throw new ServiceException(USER_BLOCKED);

        return user;

    }

    @Override
    public User checkUsernameChange(HttpSession session, String name) {
        if (name == null || name.isBlank())
            throw new ServiceException(NAME_NOT_VALID);

        User user = (User) session.getAttribute(USER_PARAMETER);
        if (!user.getName().equals(name)){
            userDao.changeUserNameById(user.getId(), name);
            user.setName(name);
        }
        return user;
    }

    @Override
    public int getNumberOfUsers() {
        return userDao.getNumberOfUsers();
    }

    @Override
    public List<User> findUsersPagination(int currentPage, int rowsPerPage) {
        int start = currentPage * rowsPerPage - rowsPerPage;
        return userDao.findUsers(start, rowsPerPage);
    }

    @Override
    public boolean changeBlockedStatus(int userId, boolean blocked) {
        return userDao.changeBlockedStatus(userId, !blocked);
    }

    @Override
    public List<User> findAllManagers() {
        return userDao.findAllManagers();
    }

    @Override
    public Optional<User> createManager(String name, String password, String email, String phone) {
        validateSignUpParams(name, password, email, phone);
        String hashPassword = Security.hashPassword(password);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashPassword);
        user.setPhone(phone);
        user.setRoleId(Role.MANAGER.getRole());
        user.setBlocked(false);
        userDao.create(user);
        return Optional.ofNullable(userDao.findUserByEmail(email));
    }

    /**
     * Sign Up form validation
     */
    private void validateSignUpParams(String name, String password, String email, String phone) throws ServiceException{
        if (!Security.isEmailValid(email))
            throw new ServiceException(EMAIL_NOT_VALID);

        if (!Security.isPasswordValid(password))
            throw new ServiceException(PASSWORD_NOT_VALID);

        if (name == null || name.isBlank())
            throw new ServiceException(NAME_NOT_VALID);

        if (!Security.isPhoneValid(phone))
            throw new ServiceException(PHONE_NOT_VALID);

        if (userDao.findUserByEmail(email) != null)
            throw new ServiceException(EMAIL_EXISTS);
    }

    /**
     * Login form validation
     */
    private void validateLoginParams(String email, String password) throws ServiceException{
        if (!Security.isEmailValid(email))
            throw new ServiceException(EMAIL_NOT_VALID);

        if (!Security.isPasswordValid(password))
            throw new ServiceException(PASSWORD_NOT_VALID);
    }
}
