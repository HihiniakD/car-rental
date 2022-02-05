package model.service.impl;

import model.dao.CarDao;
import model.dao.factory.DaoFactory;
import model.entity.Car;
import model.entity.enums.Status;
import model.entity.enums.Transmission;
import model.exception.ServiceException;
import model.service.CarService;
import model.util.DateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import static controller.Constants.*;

public class CarServiceImpl implements CarService {
    CarDao carDao = DaoFactory.getInstance().createCarDao();


    @Override
    public Boolean addCar(String model, int passengers, int price, Status status, Transmission transmission, String category, int cityId, String imageUrl) {
        return null;
    }

    @Override
    public List<Car> findAllAvailableCars(int cityId, int categoryId, int brandId, String pickUpDate,
                                          String dropOffDate , HttpServletRequest request) {
        long days = DateService.countDays(pickUpDate, dropOffDate);
        List<Car> cars = carDao.findAllAvailableCars(cityId, categoryId, brandId);
        HttpSession session = request.getSession(true);
        session.setAttribute(PICKUP_DATE_PARAMETER, pickUpDate);
        session.setAttribute(DROPOFF_DATE_PARAMETER, dropOffDate);
        session.setAttribute(DAYS_PARAMETER, days);
        return cars;
    }

    @Override
    public List<Car> findAllCars() {
        return null;
    }

    @Override
    public Car findCarById(int id) {
        Car car = carDao.findById(id);
        if (car.getStatusId().getStatus() == Status.BUSY.getStatus())
            throw new ServiceException(CAR_NOT_AVAILABLE_ERROR);

        return car;
    }

    @Override
    public boolean carIsAvailable(int id) {
        boolean isAvailable = carDao.checkIsAvailable(id);
        if (!isAvailable)
            throw new ServiceException(CAR_NOT_AVAILABLE_ERROR);
        return isAvailable;
    }

    @Override
    public Boolean changeStatus(int id, Status status) {
        return carDao.changeCarStatus(id, status);
    }

    @Override
    public List<Car> findAllAvailableCarsSortedByPrice(int cityId, int categoryId, int brandId) {
        return carDao.findAllAvailableCarsSortedByPrice(cityId, categoryId, brandId);
    }

    @Override
    public List<Car> findAllAvailableCarsSortedByName(int cityId, int categoryId, int brandId) {
        return carDao.findAllAvailableCarsSortedByName(cityId, categoryId, brandId);
    }
}
