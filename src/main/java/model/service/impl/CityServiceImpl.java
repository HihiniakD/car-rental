package model.service.impl;

import model.dao.CityDao;
import model.dao.factory.DaoFactory;
import model.entity.City;
import model.service.CityService;

import java.util.List;

public class CityServiceImpl implements CityService {

    private final CityDao cityDao = DaoFactory.getInstance().createCityDao();

    @Override
    public List<City> findAllCities() {
        return cityDao.findAllCities();
    }

    @Override
    public String getCityNameById(int id) {
        return cityDao.getCityNameById(id);
    }
}
