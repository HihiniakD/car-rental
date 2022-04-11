package controller;

public class Constants {

    //errors constants
    public static final String EMAIL_NOT_VALID = "emailNotValid";
    public static final String PASSWORD_NOT_VALID = "passwordNotValid";
    public static final String NAME_NOT_VALID = "nameNotValid";
    public static final String PHONE_NOT_VALID = "phoneNotValid";
    public static final String USER_NOT_FOUND = "userNotFound";
    public static final String DATA_NOT_VALID = "dataNotValid";
    public static final String USER_BLOCKED = "userBlocked";
    public static final String EMAIL_EXISTS = "emailExists";
    public static final String WRONG_PASSWORD = "wrongPassword";
    public static final String DATE_NOT_VALID = "dateNotValid";
    public static final String OTHER_ERROR = "otherError";
    public static final String CAR_NOT_AVAILABLE_ERROR = "carNotAvailable";
    public static final String CC_NAME_NOT_VALID_ERROR = "ccNameNotValid";
    public static final String CC_NUMBER_NOT_VALID_ERROR = "ccNumberNotValid";
    public static final String CC_EXPIRATION_NOT_VALID_ERROR = "ccExpirationNotValid";
    public static final String CC_CVV_NOT_VALID_ERROR = "ccCvvNotValid";
    public static final String MUST_LOGIN = "mustLogin";

    //constants for retrieving and inserting from/into requests
    public static final String USER_PARAMETER = "user";
    public static final String USERS_PARAMETER = "users";
    public static final String ERROR_PARAMETER = "error";
    public static final String NAME_PARAMETER = "name";
    public static final String PHONE_PARAMETER = "phone";
    public static final String EMAIL_PARAMETER = "email";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String CITY_PARAMETER = "city";
    public static final String BRAND_PARAMETER = "brand";
    public static final String CATEGORY_PARAMETER = "category";
    public static final String PICKUP_DATE_PARAMETER = "pickupDate";
    public static final String DROPOFF_DATE_PARAMETER = "dropoffDate";
    public static final String DAYS_PARAMETER = "days";
    public static final String CARS_PARAMETER = "cars";
    public static final String CAR_PARAMETER = "car";
    public static final String ID_PARAMETER = "id";
    public static final String CITIES_PARAMETER = "cities";
    public static final String BRANDS_PARAMETER = "brands";
    public static final String CATEGORIES_PARAMETER = "categories";
    public static final String DRIVER_PARAMETER = "driver";
    public static final String DRIVER_PRICE_PARAMETER = "driver_price";
    public static final String TOTAL_PRICE_PARAMETER = "total_price";
    public static final String CAR_RENTAL_PRICE_PARAMETER = "car_rental_price";
    public static final String CC_NAME_PARAMETER = "cc_name";
    public static final String CC_NUMBER_PARAMETER = "cc_number";
    public static final String CC_EXPIRATION_PARAMETER = "cc_expiration";
    public static final String CC_CVV_PARAMETER = "cvv";
    public static final String BRAND_ID_PARAMETER = "brand_id";
    public static final String CITY_ID_PARAMETER = "city_id";
    public static final String CATEGORY_ID_PARAMETER = "category_id";
    public static final String SUCCESS_MESSAGE_PARAMETER = "success_message";
    public static final String MESSAGE_PARAMETER = "message";
    public static final String ORDERS_PARAMETER = "orders";
    public static final String ORDER_PARAMETER = "order";
    public static final String NEW_ORDERS_PARAMETER = "newOrders";
    public static final String IN_PROGRESS_ORDERS_PARAMETER = "ordersInProgress";
    public static final String FINISHED_ORDERS_PARAMETER = "finishedOrders";
    public static final String DECLINED_ORDERS_PARAMETER = "declinedOrders";
    public static final String COMMENT_PARAMETER = "comment";
    public static final String CAR_ID_PARAMETER = "carId";
    public static final String PENALTY_PARAMETER = "penalty";
    public static final String CURRENT_PAGE_PARAMETER = "currentPage";
    public static final String NUMBER_OF_PAGES_PARAMETER = "numberOfPages";
    public static final String BLOCKED_PARAMETER = "blocked";
    public static final String MANAGERS_PARAMETER = "managers";
    public static final String PRICE_PARAMETER = "price";
    public static final String URL_PARAMETER = "url";
    public static final String TRANSMISSION_PARAMETER = "transmission";
    public static final String MODEL_PARAMETER = "model";
    public static final String PASSENGERS_PARAMETER = "passengers";

    //redirect messages constants
    public static final String SUCCESS_BOOKING_MESSAGE = "successBookingMessage";
    public static final String SUCCESS_SIGN_UP_MESSAGE = "successSignUpMessage";
    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String FAIL_MESSAGE = "failMessage";

    //other constants
    public static final String BACK_TO_INDEX = "to index";
    public static final String EMPTY_COMMAND = "";
    public static final String NO_SUCH_INDEX = "No such index";

    //constants for logging
    public static final String SQL_GET_CONNECTION_ERROR = "Exception thrown while getting connection";
    public static final String SQL_CLOSE_CONNECTION_ERROR = "Exception thrown while closing connection";
    public static final String SQL_QUERY_ERROR = "Exception thrown while querying ";
    public static final String SQL_ROLLBACK_ERROR = "Exception thrown while rollback ";
    public static final String SQL_CHANGE_AUTO_COMMIT_ERROR = "Exception thrown while changing auto commit to true value ";
    public static final String DB_CLASS_NOT_FOUND_ERROR = "ClassNot Found exception thrown";
    public static final String DB_ACCESS_ERROR = "Can`t access data base";
}
