package model.dao;

import model.entity.City;

import java.util.List;

public interface CityDao extends GenericDao<City> {
    List<City> findAllCities();
    String getCityNameById(int id);
}
