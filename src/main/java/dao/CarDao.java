package dao;

import model.entity.Car;
import model.entity.enums.Status;

import java.util.List;

public interface CarDao extends GenericDao<Car>{
    boolean deleteCar(int carId);
    boolean editCar(int carId, int price, String imageUrl);
    List<Car> findAllAvailableCars(int cityId, int categoryId, int brandId);
    List<Car> findAllCars();
    Car findById(int id);
    boolean checkIsAvailable(int id);
    boolean changeCarStatus(int id, Status status);
    List<Car> findAllAvailableCarsSortedByPrice(int cityId, int categoryId, int brandId);
    List<Car> findAllAvailableCarsSortedByName(int cityId, int categoryId, int brandId);
}
