package model.dao;

import model.entity.Car;

import java.util.List;

public interface CarDao extends GenericDao<Car>{
    List<Car> findAllAvailableCars(int cityId, int categoryId, int brandId);
    List<Car> findAllCars();
    Car findById(int id);
}
