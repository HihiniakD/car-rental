package model.service;

import model.entity.Car;
import model.entity.enums.Status;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CarService {
    Boolean addCar(int brandId, String model, int passengers, String price, String transmission, int categoryId, int cityId, String imageUrl);
    List<Car> findAllAvailableCars(int cityId, int categoryId, int brandId, String pickUpDate,
                                   String dropOffDate , HttpServletRequest request);
    List<Car> findAllCars();
    Car findCarById(int id);
    boolean carIsAvailable(int id);
    Boolean changeStatus(int id, Status status);
    List<Car> findAllAvailableCarsSortedByPrice(int cityId, int categoryId, int brandId);
    List<Car> findAllAvailableCarsSortedByName(int cityId, int categoryId, int brandId);
    boolean deleteCar(int carId);
    boolean editCar(int carId, String price, String imageUrl);


}
