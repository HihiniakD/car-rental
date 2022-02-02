package model.service;

import model.entity.Car;
import model.entity.City;
import model.entity.enums.Status;
import model.entity.enums.Transmission;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CityService {
    List<City> findAllCities();
}
