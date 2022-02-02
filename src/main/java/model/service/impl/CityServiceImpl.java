package model.service.impl;

import model.dao.BrandDao;
import model.dao.CityDao;
import model.dao.factory.DaoFactory;
import model.entity.Brand;
import model.entity.City;
import model.service.BrandService;
import model.service.CityService;

import java.util.List;

public class CityServiceImpl implements CityService {
    CityDao cityDao = DaoFactory.getInstance().createCityDao();


    @Override
    public List<City> findAllCities() {
        return cityDao.findAllCities();
    }
}
