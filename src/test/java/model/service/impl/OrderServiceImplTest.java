package model.service.impl;

import controller.Constants;
import dao.factory.DaoFactory;
import dao.impl.JDBCCarImpl;
import dao.impl.JDBCOrderImpl;
import model.entity.Car;
import model.entity.Order;
import model.entity.OrderExtended;
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
import service.impl.OrderServiceImpl;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl serviceUnderTest;

    @Mock
    private JDBCOrderImpl orderDao;

    @Mock
    private DaoFactory daoFactory;

    private OrderServiceImpl orderService;

    public static final int ID = 1;
    public static final String EMAIL = "email@gmail.com";
    public static final String NAME = "Steve Rambo";
    public static final String PASSWORD = "Qwerty90@";
    public static final String PHONE = "380961111111";
    public static final int ROLE = 1;
    public static final int PRICE = 100;
    public static final int PASSENGERS = 4;
    public static final String MODEL = "Q8";
    public static final String TRANSMISSION = "MANUAL";
    public static final String URL = "https://www.kimballstock.com/pix/car/p/05/aut-48-iz0073-01p.jpg";
    public static final String COMMENT = "Some comment content";
    public static final String PICK_UP_DATE = "2023-01-01";
    public static final String DROP_OFF_DATE = "2023-01-05";
    public static final String PENALTY = "50";
    public static final String NOT_VALID_PENALTY = "50$";
    public static final String BLANK_FIELD = " ";


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl();
        when(daoFactory.createOrderDao()).thenReturn(orderDao);
    }


    @Test
    public void shouldReturnTrueWhenInputDataIsValidFinishOrder() {
        Order testOrder = new Order();
        testOrder.setId(ID);
        testOrder.setUserId(ID);
        testOrder.setCarId(ID);
        testOrder.setTotalPrice(PRICE);
        testOrder.setStatusId(Status.BUSY);
        testOrder.setCityId(ID);
        testOrder.setPickupDate(LocalDate.parse(PICK_UP_DATE));
        testOrder.setDropoffDate(LocalDate.parse(DROP_OFF_DATE));
        testOrder.setComment(null);
        testOrder.setWithDriver(true);
        Mockito.when(orderDao.findById(anyInt())).thenReturn(testOrder);
        Mockito.when(orderDao.finishOrderAndSetStatusAndPrice(anyInt(), anyString(), anyInt(), anyObject())).thenReturn(true);

        boolean res = serviceUnderTest.finishOrder(ID, COMMENT, PENALTY);

        Assert.assertTrue(res);
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidPenaltyFeeFinishOrder(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.finishOrder(ID, COMMENT, NOT_VALID_PENALTY); }
        );

        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithBlankPenaltyFeeFinishOrder(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.finishOrder(ID, COMMENT, BLANK_FIELD); }
        );

        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithNullPenaltyFeeFinishOrder(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.finishOrder(ID, COMMENT, null); }
        );

        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }

    @Test
    public void findOrderById(){
        Order order = orderService.findOrderById(ID);
        assertNotNull(order);
    }

    @Test
    public void findAllFinishedOrders(){
        List<OrderExtended> orders = orderService.findAllFinishedOrders();
        assertNotNull(orders);
    }

    @Test
    public void findAllNewOrders(){
        List<OrderExtended> orders = orderService.findAllNewOrders();
        assertNotNull(orders);
    }

    @Test
    public void findAllInProgressOrders(){
        List<OrderExtended> orders = orderService.findAllInProgressOrders();
        assertNotNull(orders);
    }

    @Test
    public void findAllDeclinedOrders(){
        List<OrderExtended> orders = orderService.findAllDeclinedOrders();
        assertNotNull(orders);
    }

    @Test
    public void findAllByUserId(){
        List<OrderExtended> orders = orderService.findAllByUserId(1);
        assertNotNull(orders);
    }

    @Test
    public void shouldPassWithValidInputProcessOrder(){
        Mockito.when(orderDao.create(createOrder())).thenReturn(true);
        boolean res = serviceUnderTest.processOrder(createUser(), createCar(), PICK_UP_DATE, DROP_OFF_DATE, PRICE, false);
        assertFalse(res);
    }

    @Test
    public void calculateCarDriverPrice(){
        assertEquals(100, serviceUnderTest.calculateCarDriverPrice(5));
    }

    @Test
    public void calculateTotalPrice(){
        assertEquals(500, serviceUnderTest.calculateTotalPrice(100, 5));
    }

    private User createUser(){
        User user = new User();
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setPassword(PASSWORD);
        user.setPhone(PHONE);
        user.setBlocked(false);
        user.setRoleId(ROLE);
        return user;
    }

    private Car createCar(){
        Car car = new Car();
        car.setCityId(ID);
        car.setPrice(PRICE);
        car.setCategoryId(ID);
        car.setTransmission(Transmission.MANUAL);
        car.setImageUrl(MODEL);
        car.setImageUrl(URL);
        car.setPassengers(PASSENGERS);
        car.setStatusId(Status.AVAILABLE);
        return car;
    }

    private Order createOrder(){
        Order order = new Order();
        order.setWithDriver(false);
        order.setCityId(ID);
        order.setTotalPrice(PRICE);
        order.setStatusId(Status.PROCESSING);
        order.setCarId(ID);
        order.setUserId(ID);
        order.setComment(COMMENT);
        order.setDropoffDate(LocalDate.parse(DROP_OFF_DATE));
        order.setPickupDate(LocalDate.parse(PICK_UP_DATE));
        return order;
    }




}