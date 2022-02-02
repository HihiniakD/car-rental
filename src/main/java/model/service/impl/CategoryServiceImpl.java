package model.service.impl;

import model.dao.CategoryDao;
import model.dao.factory.DaoFactory;
import model.entity.Brand;
import model.entity.Category;
import model.service.BrandService;
import model.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = DaoFactory.getInstance().createCategoryDao();


    @Override
    public List<Category> findAllCategories() {
        return categoryDao.findAllCategories();
    }
}
