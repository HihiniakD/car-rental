package model.service.impl;

import model.dao.BrandDao;
import model.dao.factory.DaoFactory;
import model.entity.Brand;
import model.entity.Order;
import model.entity.User;
import model.service.BrandService;
import model.service.OrderService;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    BrandDao brandDao = DaoFactory.getInstance().createBrandDao();


    @Override
    public List<Brand> findAllBrands() {
        return brandDao.findAllABrands();
    }
}
