package model.entity;

import model.entity.enums.Status;
import model.entity.enums.Transmission;

public class Car {
    private int id;
    private int brandId;
    private String model;
    private int passengers;
    private int price;
    private Status statusId;
    private Transmission transmission;
    private int cityId;
    private int categoryId;
    private String imageUrl;

    public Car(){}

    public Car(int id, int brandId, String model, int passengers, int price, Status statusId, Transmission transmission, int cityId, int categoryId, String imageUrl) {
        this.id = id;
        this.brandId = brandId;
        this.model = model;
        this.passengers = passengers;
        this.price = price;
        this.statusId = statusId;
        this.transmission = transmission;
        this.cityId = cityId;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", model='" + model + '\'' +
                ", passengers=" + passengers +
                ", price=" + price +
                ", statusId=" + statusId +
                ", transmission=" + transmission +
                ", cityId=" + cityId +
                ", categoryId=" + categoryId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
