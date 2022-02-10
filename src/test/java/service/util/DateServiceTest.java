package service.util;

import org.junit.Assert;
import org.junit.Test;
import service.exception.ServiceException;

import java.time.LocalDate;

public class DateServiceTest {

    public static final String VALID_PICK_UP_DAY = "2022-04-14";
    public static final String VALID_DROP_OFF_DAY = "2022-04-20";

    @Test
    public void shouldReturnDaysWhenInputDataIsValid(){
        long expectedDays = 6L;
        long days = DateService.countDays(VALID_PICK_UP_DAY, VALID_DROP_OFF_DAY);
        Assert.assertEquals(expectedDays, days);
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhenPickUpDateIsToday(){
        String pickUpDate = LocalDate.now().toString();
        DateService.countDays(pickUpDate, VALID_DROP_OFF_DAY);
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhenPickUpDateIsInThePast(){
        String pickUpDate = LocalDate.now().minusDays(1).toString();
        DateService.countDays(pickUpDate, VALID_DROP_OFF_DAY);
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhenDropOffDateIsToday(){
        String dropOfDays = LocalDate.now().toString();
        DateService.countDays(VALID_PICK_UP_DAY, dropOfDays);
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhenDropOffDateIsInThePast(){
        String dropOfDays = LocalDate.now().minusDays(1).toString();
        DateService.countDays(VALID_PICK_UP_DAY, dropOfDays);
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhenDropOffDateIsTomorrow(){
        String dropOfDays = LocalDate.now().plusDays(1).toString();
        DateService.countDays(VALID_PICK_UP_DAY, dropOfDays);
    }

}