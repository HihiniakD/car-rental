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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl serviceUnderTest;

    @Mock
    private JDBCCarImpl carDao;

    @Mock
    private DaoFactory daoFactory;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(daoFactory.createCarDao()).thenReturn(carDao);
    }

    @Test
    public void shouldReturnCarWhenInputDataIsValidAddCar() {
        String validModel = "Audi Q7";
        String validPrice = "55";
        String validImgUrl = "https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg";
        Mockito.when(carDao.create(Mockito.any(Car.class))).thenReturn(true);

        boolean res = serviceUnderTest.addCar(1, validModel, 4, validPrice, "MANUAL", 1,
                1, validImgUrl);

        Assert.assertTrue(res);
    }


    @Test
    public void shouldThrowServiceExceptionWithInvalidPriceAddCar(){
       int brandId = 1;
       String model = "Audi Q7";
       int passengers = 4;
       String invalidPrice = "price";
       String transmission = "MANUAL";
       int categoryId = 3;
       int cityId = 1;
       String imgUrl = "https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.addCar(brandId, model, passengers, invalidPrice, transmission, categoryId,
                        cityId, imgUrl); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidImgUrlAddCar(){
        int brandId = 1;
        String model = "Audi Q7";
        int passengers = 4;
        String price = "70";
        String transmission = "MANUAL";
        int categoryId = 3;
        int cityId = 1;
        String invalidImgUrl = "someText";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.addCar(brandId, model, passengers, price, transmission, categoryId,
                        cityId, invalidImgUrl); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidImgUrlEditCar(){
        int carId = 5;
        String price = "70";
        String invalidImgUrl = "someText";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.editCar(carId, price, invalidImgUrl); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidPriceEditCar(){
        int carId = 5;
        String price = "ad";
        String invalidImgUrl = "https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.editCar(carId, price, invalidImgUrl); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWhenCarIsBusyCarIsAvailable(){
        Mockito.when(carDao.checkIsAvailable(anyInt())).thenReturn(false);
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.carIsAvailable(1); }
        );

        assertEquals(Constants.CAR_NOT_AVAILABLE_ERROR,exception.getMessage());
    }

}