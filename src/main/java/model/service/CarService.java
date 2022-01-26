package model.service;

import model.entity.Car;
import model.entity.enums.Status;
import model.entity.enums.Transmission;

import java.util.List;

public interface CarService {
    Boolean addCar(String model, int passengers, int price, Status status, Transmission transmission, String category,
                   int cityId, String imageUrl);
    List<Car> findAllAvailableCars(String city);
    List<Car> findAllCars();
    Boolean changeStatus(int id, Status status);
}