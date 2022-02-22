package model.service.impl;

import controller.Constants;
import dao.factory.DaoFactory;
import dao.impl.JDBCUserImpl;
import model.entity.User;
import org.mockito.Mockito;
import service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import service.impl.UserServiceImpl;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl serviceUnderTest;

    @Mock
    private JDBCUserImpl userDao;

    @Mock
    private DaoFactory daoFactory;

    private UserServiceImpl userService;

    public static final int ID = 1;
    public static final String VALID_EMAIL = "email@gmail.com";
    public static final String NOT_VALID_EMAIL = "emailgmail.com";
    public static final String VALID_PASSWORD = "Qwerty90";
    public static final String NOT_VALID_PASSWORD = "qwey0";
    public static final String HASHED_PASSWORD = "afc66dd493e595058ed1acf22790e5427f5c7abfb1139382fc93b5aa623cfaff7e43ca3fd84a7e0b6bcd1d0a29e7ea7639a57dc43d499ac64010b8b4340cbf1a5e274b8b80ccf0c947eeec9f8d620ff1";
    public static final String VALID_NAME = "Steve Rambo";
    public static final String VALID_PHONE = "380111111111";
    public static final String NOT_VALID_PHONE = "380111";


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl();
        when(daoFactory.createUserDao()).thenReturn(userDao);
    }

    @Test
    public void shouldReturnUserWhenInputDataIsValidSignUp() {

        Mockito.when(userDao.create(Mockito.any(User.class))).thenReturn(true);
        Mockito.when(userDao.findUserByEmail(VALID_EMAIL)).thenReturn(null)
                .thenReturn(createUser(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, VALID_PHONE));

        User testUser = serviceUnderTest.signUpUser(VALID_NAME, VALID_PASSWORD, VALID_EMAIL, VALID_PHONE).get();

        Assert.assertNotNull(testUser);
    }

    private static User createUser(String validEmail,
                                   String validPassword,
                                   String validName,
                                   String validPhoneNumber) {

        return new User(ID, validName, validPassword, validEmail, validPhoneNumber, ID, false);
    }

    @Test
    public void shouldThrowServiceExceptionWithMessageEmailNotValidSignUp(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.signUpUser(VALID_NAME,VALID_PASSWORD,NOT_VALID_EMAIL,VALID_PHONE); }
        );

        assertEquals(Constants.EMAIL_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePasswordNotValidSignUp(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.signUpUser(VALID_NAME, NOT_VALID_PASSWORD,VALID_EMAIL,VALID_PHONE); }
        );

        assertEquals(Constants.PASSWORD_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePhoneNotValidSignUp(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.signUpUser(VALID_NAME, VALID_PASSWORD,VALID_EMAIL,NOT_VALID_PHONE); }
        );

        assertEquals(Constants.PHONE_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldReturnUserWhenInputDataIsValidLogin() throws Exception {
        String validEmail = "test@gmail.com";
        String validPassword = "Qwerty90@";
        String hashedPassword = "afc66dd493e595058ed1acf22790e5427f5c7abfb1139382fc93b5aa623cfaff7e43ca3fd84a7e0b6bcd1d0a29e7ea7639a57dc43d499ac64010b8b4340cbf1a5e274b8b80ccf0c947eeec9f8d620ff1";
        User user = new User();
        user.setName(VALID_NAME);
        user.setEmail(VALID_EMAIL);
        user.setPassword(hashedPassword);
        user.setBlocked(false);
        user.setRoleId(ID);
        Mockito.when(userDao.findUserByEmail(validEmail)).thenReturn(user);

        User testUser = serviceUnderTest.loginUser(validEmail, validPassword);

        Assert.assertNotNull(testUser);
    }

    @Test
    public void shouldReturnExceptionWithMessageUserNotFoundLogin() throws Exception {

        Mockito.when(userDao.findUserByEmail(VALID_EMAIL)).thenReturn(null);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.loginUser(VALID_EMAIL, VALID_PASSWORD); }
        );
        assertEquals(Constants.USER_NOT_FOUND,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWithMessageWrongPasswordLogin() throws Exception {
        String email = "test@gmail.com";
        String wrongPassword = "Qwerty90";
        String hashedPassword = "afc66dd493e595058ed1acf22790e5427f5c7abfb1139382fc93b5aa623cfaff7e43ca3fd84a7e0b6bcd1d0a29e7ea7639a57dc43d499ac64010b8b4340cbf1a5e274b8b80ccf0c947eeec9f8d620ff1";
        User user = new User();
        user.setName("Dmytro");
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setBlocked(false);
        user.setRoleId(1);
        Mockito.when(userDao.findUserByEmail(email)).thenReturn(user);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.loginUser(email, wrongPassword); }
        );
        assertEquals(Constants.WRONG_PASSWORD,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWithMessageUserBlockedLogin() throws Exception {
        String email = "test@gmail.com";
        String wrongPassword = "Qwerty90@";
        String hashedPassword = "afc66dd493e595058ed1acf22790e5427f5c7abfb1139382fc93b5aa623cfaff7e43ca3fd84a7e0b6bcd1d0a29e7ea7639a57dc43d499ac64010b8b4340cbf1a5e274b8b80ccf0c947eeec9f8d620ff1";
        User user = new User();
        user.setName("Dmytro");
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setBlocked(true);
        user.setRoleId(1);
        Mockito.when(userDao.findUserByEmail(email)).thenReturn(user);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.loginUser(email, wrongPassword); }
        );
        assertEquals(Constants.USER_BLOCKED,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessageEmailNotValidLogin(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.loginUser(NOT_VALID_EMAIL, VALID_PASSWORD); }
        );

        assertEquals(Constants.EMAIL_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePasswordNotValidLogin(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.loginUser(VALID_EMAIL, NOT_VALID_PASSWORD); }
        );
        assertEquals(Constants.PASSWORD_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldReturnUserWhenInputDataIsValidCreateManager() {
        Mockito.when(userDao.create(Mockito.any(User.class))).thenReturn(true);
        Mockito.when(userDao.findUserByEmail(VALID_EMAIL)).thenReturn(null)
                .thenReturn(createUser(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, VALID_PHONE));

        User testUser = serviceUnderTest.createManager(VALID_NAME, VALID_PASSWORD, VALID_EMAIL, VALID_PHONE).get();

        Assert.assertNotNull(testUser);
    }

    @Test
    public void shouldThrowServiceExceptionWithMessageEmailNotValidCreateManager(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.createManager(VALID_NAME,VALID_PASSWORD,NOT_VALID_EMAIL,VALID_PHONE); }
        );

        assertEquals(Constants.EMAIL_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePasswordNotValidCreateManager(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.createManager(VALID_NAME, NOT_VALID_PASSWORD,VALID_EMAIL,VALID_PHONE); }
        );

        assertEquals(Constants.PASSWORD_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePhoneNotValidCreateManager(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.createManager(VALID_NAME, VALID_PASSWORD,VALID_EMAIL,NOT_VALID_PHONE); }
        );

        assertEquals(Constants.PHONE_NOT_VALID,exception.getMessage());
    }

    @Test
    public void findAllManagers(){
        List<User> users = userService.findAllManagers();
        assertNotNull(users);
    }

    @Test
    public void getNumberOfUsers(){
        int users = userService.getNumberOfUsers();
        assertTrue(users > 1);
    }

    @Test
    public void changeBlockedStatus(){
        Mockito.when(userDao.changeBlockedStatus(anyInt(), anyBoolean())).thenReturn(true);
        boolean res = userService.changeBlockedStatus(ID, true);
        assertTrue(res);
    }



}