package dao;

import model.entity.Brand;

import java.util.List;

public interface BrandDao extends GenericDao<Brand>{
    List<Brand> findAllABrands();
}
