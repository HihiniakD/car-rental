package dao.mapper;

import model.entity.OrderExtended;
import model.entity.enums.Status;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderExtendedMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_USER_ID = "user_id";
    private static final String FIELD_USER_NAME = "user_name";
    private static final String FIELD_CAR_ID = "car_id";
    private static final String FIELD_CAR_NAME = "car_name";
    private static final String FIELD_CITY_ID = "city_id";
    private static final String FIELD_CITY_NAME = "city_name";
    private static final String FIELD_PICK_UP_DATE = "pickup_date";
    private static final String FIELD_DROP_OFF_DATE = "dropoff_date";
    private static final String FIELD_TOTAL_PRICE = "total_price";
    private static final String FIELD_STATUS_ID = "status_id";
    private static final String FIELD_DRIVER = "driver";
    private static final String FIELD_COMMENT = "comment";


    public static OrderExtended map(ResultSet resultSet) throws SQLException {
        OrderExtended order = new OrderExtended();
        order.setId(resultSet.getInt(FIELD_ID));
        order.setUserId(resultSet.getInt(FIELD_USER_ID));
        order.setUserName(resultSet.getString(FIELD_USER_NAME));
        order.setCarId(resultSet.getInt(FIELD_CAR_ID));
        order.setCarName(resultSet.getString(FIELD_CAR_NAME));
        order.setCityId(resultSet.getInt(FIELD_CITY_ID));
        order.setCityName(resultSet.getString(FIELD_CITY_NAME));
        Date pickUp =  (Date) resultSet.getObject(FIELD_PICK_UP_DATE);
        order.setPickupDate(pickUp.toLocalDate());
        Date dropOff =  (Date) resultSet.getObject(FIELD_DROP_OFF_DATE);
        order.setDropoffDate(dropOff.toLocalDate());
        order.setTotalPrice(resultSet.getInt(FIELD_TOTAL_PRICE));
        order.setStatusId(Status.geStatus(resultSet.getInt(FIELD_STATUS_ID)));
        order.setWithDriver(resultSet.getBoolean(FIELD_DRIVER));
        order.setComment(resultSet.getString(FIELD_COMMENT));

        return order;
    }
}
