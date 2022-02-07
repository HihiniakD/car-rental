package model.service.impl;

import model.dao.BrandDao;
import model.dao.factory.DaoFactory;
import model.entity.Brand;
import model.service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    private final BrandDao brandDao = DaoFactory.getInstance().createBrandDao();

    @Override
    public List<Brand> findAllBrands() {
        return brandDao.findAllABrands();
    }
}
