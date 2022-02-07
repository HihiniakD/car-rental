package model.service;

import model.entity.City;

import java.util.List;

public interface CityService {
    List<City> findAllCities();
    String getCityNameById(int id);
}
