package model.service;

import model.entity.Brand;
import model.entity.City;

import java.util.List;

public interface BrandService {
    List<Brand> findAllBrands();
}
