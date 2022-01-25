package model.entity;

import model.entity.enums.Status;
import model.entity.enums.Transmission;

public class Car {
    private int id;
    private String model;
    private int passengers;
    private int price;
    private Status status;
    private Transmission transmission;
    private String category;
    private int cityId;
    private String imageUrl;

    public Car(){}

    public Car(int id, String model, int passengers, int price, Status status, Transmission transmission, String category, int city_id, String imageUrl) {
        this.id = id;
        this.model = model;
        this.passengers = passengers;
        this.price = price;
        this.status = status;
        this.transmission = transmission;
        this.category = category;
        this.cityId = city_id;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
                ", model='" + model + '\'' +
                ", passengers=" + passengers +
                ", price=" + price +
                ", status=" + status +
                ", transmission=" + transmission +
                ", category='" + category + '\'' +
                ", city_id=" + cityId +
                ", image_url='" + imageUrl + '\'' +
                '}';
    }
}
