package model.service.impl;

import controller.security.Security;
import model.dao.UserDao;
import model.dao.impl.factory.JDBCDaoFactory;
import model.entity.User;
import model.entity.enums.Role;
import model.exception.ServiceException;
import model.service.UserService;

import java.util.Optional;

import static controller.Constants.*;


public class UserServiceImpl implements UserService {

     private UserDao userDao = JDBCDaoFactory.getInstance().createUserDao();

    @Override
    public Optional<User> signUpUser(String name, String password, String email, String phone) throws ServiceException{
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

        //ПЕРЕПИСАТЬ
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

    private void validateLoginParams(String email, String password) throws ServiceException{
        if (!Security.isEmailValid(email))
            throw new ServiceException(EMAIL_NOT_VALID);

        if (!Security.isPasswordValid(password))
            throw new ServiceException(PASSWORD_NOT_VALID);
    }

}
