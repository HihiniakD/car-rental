package model.util;

public class PriceSevice {
    private static final int PRICE_FOR_DAY = 20;

    public static long calculateTotalPrice(int price, long days){
        return price * days;
    }

    public static long calculateCarDrivePrice(long days){
        return PRICE_FOR_DAY * days;
    }

}
