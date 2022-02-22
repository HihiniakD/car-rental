package service.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PriceServiceTest {
    public static final int PRICE = 50;
    public static final int DAYS = 5;
    public static final long RESULT_PRICE = 250;
    public static final long RESULT_DAYS = 100;

    @Test
    public void shouldReturnRightResultCalculateTotalPrice(){

        Assert.assertEquals(RESULT_PRICE, PriceService.calculateTotalPrice(PRICE, DAYS));
    }

    @Test
    public void shouldReturnRightResultCalculateCarDrivePrice(){

        Assert.assertEquals(RESULT_DAYS, PriceService.calculateCarDrivePrice(DAYS));
    }

}