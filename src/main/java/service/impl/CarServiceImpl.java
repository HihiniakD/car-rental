package service.impl;

import controller.security.Security;
import dao.CarDao;
import dao.factory.DaoFactory;
import model.entity.Car;
import model.entity.enums.Status;
import model.entity.enums.Transmission;
import service.exception.ServiceException;
import service.CarService;
import service.util.DateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import static controller.Constants.*;

/**
 * Implementation of CarService
 */
public class CarServiceImpl implements CarService {

    private CarDao carDao = DaoFactory.getInstance().createCarDao();

    @Override
    public Boolean addCar(int brandId, String model, int passengers, String price, String transmission, int categoryId, int cityId, String imageUrl) {
        validateAddCar(price, model, imageUrl);
        Car car = new Car();
        car.setBrandId(brandId);
        car.setModel(model);
        car.setPassengers(passengers);
        car.setPrice(Integer.parseInt(price));
        car.setStatusId(Status.AVAILABLE);
        car.setTransmission(Transmission.valueOf(transmission));
        car.setCityId(cityId);
        car.setCategoryId(categoryId);
        car.setImageUrl(imageUrl);
        return carDao.create(car);
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
        return carDao.findAllCars();
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

    @Override
    public boolean deleteCar(int carId) {
        return carDao.deleteCar(carId);
    }

    @Override
    public boolean editCar(int carId, String price, String imageUrl) {
        validateEditCar(price, imageUrl);
        int carPrice = Integer.parseInt(price);
        return carDao.editCar(carId, carPrice, imageUrl);
    }

    private boolean validateEditCar(String price, String imageUrl) {
        try{
            Integer.parseInt(price);
        }catch (NumberFormatException e){
            throw new ServiceException(DATA_NOT_VALID);
        }

        if (!Security.isUrlValid(imageUrl)){
            throw new ServiceException(DATA_NOT_VALID);
        }

        return true;
    }

    /**
     * AddCar form validation
     */
    private boolean validateAddCar(String price, String model, String imageUrl) {
        try{
            Integer.parseInt(price);
        }catch (NumberFormatException e){
            throw new ServiceException(DATA_NOT_VALID);
        }

        if (!Security.isUrlValid(imageUrl)){
            throw new ServiceException(DATA_NOT_VALID);
        }

        if (model == null || model.isBlank())
            throw new ServiceException(DATA_NOT_VALID);

        return true;
    }
}
