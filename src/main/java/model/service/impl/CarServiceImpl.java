package model.service.impl;

import model.entity.Car;
import model.entity.enums.Status;
import model.entity.enums.Transmission;
import model.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {
    @Override
    public Boolean addCar(String model, int passengers, int price, Status status, Transmission transmission, String category, int cityId, String imageUrl) {
        return null;
    }

    @Override
    public List<Car> findAllAvailableCars(String city) {
        return null;
    }

    @Override
    public List<Car> findAllCars() {
        return null;
    }

    @Override
    public Boolean changeStatus(int id, Status status) {
        return null;
    }
}
