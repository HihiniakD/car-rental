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
    private Status statusId;
    private boolean withDriver;
    private String comment;

    public Order(){}

    public Order(int id, int userId, int carId, int cityId, LocalDate pickupDate, LocalDate dropoffDate, int totalPrice, Status statusId, boolean withDriver, String comment) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.cityId = cityId;
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.totalPrice = totalPrice;
        this.statusId = statusId;
        this.withDriver = withDriver;
        this.comment = comment;
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

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public boolean isWithDriver() {
        return withDriver;
    }

    public void setWithDriver(boolean withDriver) {
        this.withDriver = withDriver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", cityId=" + cityId +
                ", pickupDate=" + pickupDate +
                ", dropoffDate=" + dropoffDate +
                ", totalPrice=" + totalPrice +
                ", statusId=" + statusId +
                ", withDriver=" + withDriver +
                ", comment='" + comment + '\'' +
                '}';
    }
}
