package model.service.impl;

import controller.Constants;
import dao.factory.DaoFactory;
import dao.impl.JDBCCarImpl;
import dao.impl.JDBCOrderImpl;
import model.entity.Car;
import model.entity.Order;
import model.entity.enums.Status;
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

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl serviceUnderTest;

    @Mock
    private JDBCOrderImpl orderDao;

    @Mock
    private DaoFactory daoFactory;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(daoFactory.createOrderDao()).thenReturn(orderDao);
    }


    @Test
    public void shouldReturnTrueWhenInputDataIsValidFinishOrder() {
        Order testOrder = new Order();
        testOrder.setId(1);
        testOrder.setUserId(1);
        testOrder.setCarId(3);
        testOrder.setTotalPrice(200);
        testOrder.setStatusId(Status.BUSY);
        testOrder.setCityId(1);
        testOrder.setPickupDate(LocalDate.parse("2022-03-17"));
        testOrder.setDropoffDate(LocalDate.parse("2022-03-20"));
        testOrder.setComment(null);
        testOrder.setWithDriver(true);
        Mockito.when(orderDao.findById(anyInt())).thenReturn(testOrder);
        Mockito.when(orderDao.finishOrderAndSetStatusAndPrice(anyInt(), anyString(), anyInt(), anyObject())).thenReturn(true);

        boolean res = serviceUnderTest.finishOrder(1, "Done", "50");

        Assert.assertTrue(res);
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidPenaltyFeeFinishOrder(){
        int id = 1;
        String comment = "Comment";
        String invalidPenaltyFee = "ff";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.finishOrder(id, comment, invalidPenaltyFee); }
        );

        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithBlankPenaltyFeeFinishOrder(){
        int id = 1;
        String comment = "Comment";
        String invalidPenaltyFee = " ";

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.finishOrder(id, comment, invalidPenaltyFee); }
        );

        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithNullPenaltyFeeFinishOrder(){
        int id = 1;
        String comment = "Comment";
        String invalidPenaltyFee = null;

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { serviceUnderTest.finishOrder(id, comment, invalidPenaltyFee); }
        );

        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }


}