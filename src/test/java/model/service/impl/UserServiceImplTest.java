package model.service.impl;

import controller.Constants;
import controller.security.Security;
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
    public void shouldReturnUserWhenInputDataIsValidSignUp() {
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
    public void shouldThrowServiceExceptionWithMessageEmailNotValidSignUp(){
        String invalidEmail = "testsdgmail.com";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.signUpUser("test","mock",invalidEmail,"etet"); }
        );

        assertEquals(Constants.EMAIL_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePasswordNotValidSignUp(){
        String invalidPassword = "3453g";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.signUpUser("test", invalidPassword,"email@gmail.com","etet"); }
        );

        assertEquals(Constants.PASSWORD_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePhoneNotValidSignUp(){
        String invalidPhone = "3809655";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.signUpUser("test", "Qwerty90@","email@gmail.com",invalidPhone); }
        );

        assertEquals(Constants.PHONE_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldReturnUserWhenInputDataIsValidLogin() throws Exception {
        String validEmail = "test@gmail.com";
        String validPassword = "Qwerty90@";
        String hashedPassword = "afc66dd493e595058ed1acf22790e5427f5c7abfb1139382fc93b5aa623cfaff7e43ca3fd84a7e0b6bcd1d0a29e7ea7639a57dc43d499ac64010b8b4340cbf1a5e274b8b80ccf0c947eeec9f8d620ff1";
        User user = new User();
        user.setName("Dmytro");
        user.setEmail(validEmail);
        user.setPassword(hashedPassword);
        user.setBlocked(false);
        user.setRoleId(1);
        Mockito.when(userDao.findUserByEmail(validEmail)).thenReturn(user);

        User testUser = serviceUnderTest.loginUser(validEmail, validPassword);

        Assert.assertNotNull(testUser);
    }

    @Test
    public void shouldReturnExceptionWithMessageUserNotFoundLogin() throws Exception {
        String notFoundEmail = "test1@gmail.com";
        String password = "Qwerty90@";
        Mockito.when(userDao.findUserByEmail(notFoundEmail)).thenReturn(null);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.loginUser(notFoundEmail, password); }
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
        String invalidEmail = "testsdgmail.com";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.loginUser(invalidEmail, "Qwerty90@"); }
        );

        assertEquals(Constants.EMAIL_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePasswordNotValidLogin(){
        String invalidPassword = "3453g";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.loginUser("email@gmail.com", invalidPassword); }
        );
        assertEquals(Constants.PASSWORD_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldReturnUserWhenInputDataIsValidCreateManager() {
        String validEmail = "test@gmail.com";
        String validPassword = "qwerty90";
        String validName = "testName";
        String validPhoneNumber = "380111111111";
        Mockito.when(userDao.create(Mockito.any(User.class))).thenReturn(true);
        Mockito.when(userDao.findUserByEmail(validEmail)).thenReturn(null)
                .thenReturn(createUser(validEmail, validPassword, validName, validPhoneNumber));

        User testUser = serviceUnderTest.createManager(validName, validPassword, validEmail, validPhoneNumber).get();

        Assert.assertNotNull(testUser);
    }

    @Test
    public void shouldThrowServiceExceptionWithMessageEmailNotValidCreateManager(){
        String invalidEmail = "testsdgmail.com";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.createManager("test","mock",invalidEmail,"etet"); }
        );

        assertEquals(Constants.EMAIL_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePasswordNotValidCreateManager(){
        String invalidPassword = "3453g";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.createManager("test", invalidPassword,"email@gmail.com","etet"); }
        );

        assertEquals(Constants.PASSWORD_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithMessagePhoneNotValidCreateManager(){
        String invalidPhone = "3809655";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.createManager("test", "Qwerty90@","email@gmail.com",invalidPhone); }
        );

        assertEquals(Constants.PHONE_NOT_VALID,exception.getMessage());
    }

}