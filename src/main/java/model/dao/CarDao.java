package model.dao;

import model.entity.Car;

import java.util.List;

public interface CarDao extends GenericDao<Car>{
    List<Car> findAllAvailableCars(String city);
    List<Car> findAllCars();
    Car findById(int id);
}
