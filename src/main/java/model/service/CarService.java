package model.service;

import model.entity.Car;
import model.entity.enums.Status;
import model.entity.enums.Transmission;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CarService {
    Boolean addCar(String model, int passengers, int price, Status status, Transmission transmission, String category,
                   int cityId, String imageUrl);
    List<Car> findAllAvailableCars(int cityId, int categoryId, int brandId, String pickUpDate,
                                   String dropOffDate , HttpServletRequest request);
    List<Car> findAllCars();
    Car findCarById(int id);

    Boolean changeStatus(int id, Status status);
}
