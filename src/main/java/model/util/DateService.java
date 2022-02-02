package model.util;

import model.exception.ServiceException;

import java.time.DateTimeException;
import java.time.LocalDate;
import static controller.Constants.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateService {

    public static long countDays(String pickUpDate, String dropOffDate){
        long days = 0;
        try{
            LocalDate pickUpLocalDate = LocalDate.parse(pickUpDate);
            LocalDate dropOffLocalDate = LocalDate.parse(dropOffDate);
            LocalDate today = LocalDate.now();
            if (pickUpLocalDate.isBefore(today) || pickUpLocalDate.isEqual(today) ||
                    dropOffLocalDate.isBefore(today) || dropOffLocalDate.isEqual(today) ||
            dropOffLocalDate.isEqual(today.plusDays(1)))
                throw new ServiceException(DATE_NOT_VALID);

            days = DAYS.between(pickUpLocalDate, dropOffLocalDate);
        }catch (DateTimeException exception){
            throw new ServiceException(DATE_NOT_VALID);
        }
        return days;
    }
}
