package model.dao;

import model.entity.Brand;
import model.entity.Car;

import java.util.List;

public interface BrandDao extends GenericDao<Brand>{
    List<Brand> findAllABrands();
}