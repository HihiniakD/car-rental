package service.impl;

import dao.CityDao;
import dao.factory.DaoFactory;
import model.entity.City;
import service.CityService;

import java.util.List;

/**
 * Implementation of CityService
 */
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
