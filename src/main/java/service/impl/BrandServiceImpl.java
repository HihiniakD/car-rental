package service.impl;

import dao.BrandDao;
import dao.factory.DaoFactory;
import model.entity.Brand;
import service.BrandService;

import java.util.List;

/**
 * Implementation of BrandService
 */
public class BrandServiceImpl implements BrandService {

    private BrandDao brandDao = DaoFactory.getInstance().createBrandDao();

    @Override
    public List<Brand> findAllBrands() {
        return brandDao.findAllABrands();
    }
}
