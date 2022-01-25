package model.entity;

import model.entity.enums.Status;

import java.time.LocalDate;

public class Order {
    private int id;
    private int userId;
    private int carId;
    private int cityId;
    private LocalDate pickupDate;
    private LocalDate dropoffDate;
    private int totalPrice;
    private Status status;

    public Order(){}

    public Order(int id, int userId, int carId, int cityId, LocalDate pickupDate, LocalDate dropoffDate, int totalPrice, Status status) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.cityId = cityId;
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(LocalDate dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + userId +
                ", car_id=" + carId +
                ", city_id=" + cityId +
                ", pickup_date=" + pickupDate +
                ", dropoff_date=" + dropoffDate +
                ", total_price=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
