package model.service.impl;

import controller.Constants;
import controller.security.Security;
import dao.factory.DaoFactory;
import dao.impl.JDBCCarImpl;
import dao.impl.JDBCUserImpl;
import model.entity.Car;
import model.entity.User;
import model.entity.enums.Status;
import model.entity.enums.Transmission;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.exception.ServiceException;
import service.impl.CarServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class CarServiceImplTest {

    public static final int ID = 15;
    public static final String MODEL = "Q7";
    public static final String VALID_PRICE = "70";
    public static final int VALID_PRICE_INT = 70;
    public static final String NOT_VALID_PRICE = "70s";
    public static final String TRANSMISSION = "MANUAL";
    public static final String VALID_URL = "https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg";
    public static final String NOT_VALID_URL = "some_url.com";
    public static final int PASSENGERS = 4;

    @InjectMocks
    private CarServiceImpl serviceUnderTest;

    @Mock
    private JDBCCarImpl carDao;

    @Mock
    private DaoFactory daoFactory;

    private CarServiceImpl carService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        carService = new CarServiceImpl();
        when(daoFactory.createCarDao()).thenReturn(carDao);
    }


    @Test
    public void shouldReturnCarWhenInputDataIsValidAddCar() {
        Mockito.when(carDao.create(Mockito.any(Car.class))).thenReturn(true);

        boolean res = serviceUnderTest.addCar(ID, MODEL, 4, VALID_PRICE, TRANSMISSION, ID,
                ID, VALID_URL);

        Assert.assertTrue(res);
    }


    @Test
    public void shouldThrowServiceExceptionWithInvalidPriceAddCar(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.addCar(ID, MODEL, PASSENGERS, NOT_VALID_PRICE, TRANSMISSION, ID,
                        ID, VALID_URL); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidImgUrlAddCar(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.addCar(ID, MODEL, PASSENGERS, VALID_PRICE, TRANSMISSION, ID,
                        ID, NOT_VALID_URL); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldPassTestWithValidInputEditCar(){
        Mockito.when(carDao.editCar(anyInt(), anyInt(), anyString())).thenReturn(true);
        boolean res = serviceUnderTest.editCar(ID, VALID_PRICE, VALID_URL);
        assertTrue(res);
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidImgUrlEditCar(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.editCar(ID, VALID_PRICE, NOT_VALID_URL); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidPriceEditCar(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.editCar(ID, NOT_VALID_PRICE, VALID_URL); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWhenCarIsBusyCarIsAvailable(){
        Mockito.when(carDao.checkIsAvailable(anyInt())).thenReturn(false);
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.carIsAvailable(ID); }
        );

        assertEquals(Constants.CAR_NOT_AVAILABLE_ERROR,exception.getMessage());
    }

    @Test
    public void findAllCars(){
        List<Car> cars = carService.findAllCars();
        assertNotNull(cars);
    }

    @Test
    public void shouldPassTestWhenInputIsValidFindCarById() {
        Mockito.when(carDao.findById(anyInt())).thenReturn(createAvailableCar());
        Car car = serviceUnderTest.findCarById(ID);
        assertNotNull(car);
    }


    @Test
    public void shouldThrowServiceExceptionWhenCarIsBusyFindCarById(){
        Mockito.when(carDao.findById(anyInt())).thenReturn(createBusyCar());
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.findCarById(ID); }
        );
        assertEquals(Constants.CAR_NOT_AVAILABLE_ERROR,exception.getMessage());
    }

    @Test
    public void findAllAvailableCarsSortedByPrice(){
        Mockito.when(carDao.findAllAvailableCarsSortedByPrice(anyInt(), anyInt(), anyInt())).thenReturn(Collections.singletonList(createAvailableCar()));
        List<Car> cars = serviceUnderTest.findAllAvailableCarsSortedByPrice(ID, ID, ID);
        assertNotNull(cars);
    }

    @Test
    public void findAllAvailableCarsSortedByName(){
        Mockito.when(carDao.findAllAvailableCarsSortedByName(anyInt(), anyInt(), anyInt())).thenReturn(Collections.singletonList(createAvailableCar()));
        List<Car> cars = serviceUnderTest.findAllAvailableCarsSortedByName(ID, ID, ID);
        assertNotNull(cars);
    }

    @Test
    public void deleteCar(){
        Mockito.when(carDao.deleteCar(anyInt())).thenReturn(true);
        assertTrue(carService.changeStatus(ID, Status.AVAILABLE));
    }

    private Car createBusyCar(){
        Car car = new Car();
        car.setModel(MODEL);
        car.setCityId(ID);
        car.setStatusId(Status.BUSY);
        car.setImageUrl(VALID_URL);
        car.setTransmission(Transmission.MANUAL);
        car.setCategoryId(ID);
        car.setPrice(VALID_PRICE_INT);
        return car;
    }

    private Car createAvailableCar(){
        Car car = new Car();
        car.setModel(MODEL);
        car.setCityId(ID);
        car.setStatusId(Status.AVAILABLE);
        car.setImageUrl(VALID_URL);
        car.setTransmission(Transmission.MANUAL);
        car.setCategoryId(ID);
        car.setPrice(VALID_PRICE_INT);
        return car;
    }

}