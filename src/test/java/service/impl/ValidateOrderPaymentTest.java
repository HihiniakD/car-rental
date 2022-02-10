package service.impl;

import controller.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.OrderService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;
import service.util.DateService;

import static org.junit.Assert.*;

public class ValidateOrderPaymentTest {

    public static final String VALID_CC_NAME = "Steve Rambo";
    public static final String VALID_CC_NUMBER = "4441114528380614";
    public static final String VALID_CC_EXPIRATION_DATA = "08/29";
    public static final String VALID_CC_EXPIRATION_CVV = "323";

    private OrderService orderService = ServiceFactory.getOrderService();

    @Test
    public void shouldReturnTrueWhenInputDataIsValid(){
        boolean res = orderService.validateOrderPayment(VALID_CC_NAME, VALID_CC_NUMBER,
                VALID_CC_EXPIRATION_DATA, VALID_CC_EXPIRATION_CVV);
        Assert.assertTrue(res);
    }

    @Test
    public void shouldReturnExceptionWhenCcNameIsBlank(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(" ", VALID_CC_NUMBER,
                        VALID_CC_EXPIRATION_DATA, VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_NAME_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcNameIsNull(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(null, VALID_CC_NUMBER,
                        VALID_CC_EXPIRATION_DATA, VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_NAME_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcNameIsNotValid(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment("Steve 45", VALID_CC_NUMBER,
                        VALID_CC_EXPIRATION_DATA, VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_NAME_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcNumberIsBlank(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, " ",
                        VALID_CC_EXPIRATION_DATA, VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_NUMBER_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcNumberIsNull(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, null,
                        VALID_CC_EXPIRATION_DATA, VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_NUMBER_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcNumberIsNotValid(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, "444111452838061455",
                        VALID_CC_EXPIRATION_DATA, VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_NUMBER_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcExpDateIsBlank(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, VALID_CC_NUMBER,
                        " ", VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_EXPIRATION_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcExpDateIsNull(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, VALID_CC_NUMBER,
                        null, VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_EXPIRATION_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcExpDateIsNotValid(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, VALID_CC_NUMBER,
                        "13/02", VALID_CC_EXPIRATION_CVV); }
        );

        assertEquals(Constants.CC_EXPIRATION_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcCvvIsBlank(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, VALID_CC_NUMBER,
                        VALID_CC_EXPIRATION_DATA, " "); }
        );

        assertEquals(Constants.CC_CVV_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcCvvIsNull(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, VALID_CC_NUMBER,
                        VALID_CC_EXPIRATION_DATA, null); }
        );

        assertEquals(Constants.CC_CVV_NOT_VALID_ERROR,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenCcCvvIsNotValid(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.validateOrderPayment(VALID_CC_NAME, VALID_CC_NUMBER,
                        VALID_CC_EXPIRATION_DATA, "22"); }
        );

        assertEquals(Constants.CC_CVV_NOT_VALID_ERROR,exception.getMessage());
    }

}