package model.service.impl;

import controller.Constants;
import dao.factory.DaoFactory;
import dao.impl.JDBCUserImpl;
import model.entity.User;
import service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.impl.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl serviceUnderTest;

    @Mock
    private JDBCUserImpl userDao;

    @Mock
    private DaoFactory daoFactory;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(daoFactory.createUserDao()).thenReturn(userDao);
    }

    @Test
    public void shouldReturnUserWhenInputDataIsValidsign() {
        String validEmail = "test@gmail.com";
        String validPassword = "qwerty90";
        String validName = "testName";
        String validPhoneNumber = "380111111111";
        Mockito.when(userDao.create(Mockito.any(User.class))).thenReturn(true);
        Mockito.when(userDao.findUserByEmail(validEmail)).thenReturn(null)
                .thenReturn(createUser(validEmail, validPassword, validName, validPhoneNumber));

        User testUser = serviceUnderTest.signUpUser(validName, validPassword, validEmail, validPhoneNumber).get();

        Assert.assertNotNull(testUser);
    }

    private static User createUser(String validEmail,
                                   String validPassword,
                                   String validName,
                                   String validPhoneNumber) {

        return new User(1, validName, validPassword, validEmail, validPhoneNumber, 1, false);
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePHONE_NOT_VALID(){ //TOdo camelCase
        String invalidEmail = "testsdgmail.com";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.signUpUser("test","mock",invalidEmail,"etet"); }
        );

        assertEquals(Constants.EMAIL_NOT_VALID,exception.getMessage());
    }

}