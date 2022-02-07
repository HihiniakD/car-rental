package model.service.impl;

import model.dao.CategoryDao;
import model.dao.factory.DaoFactory;
import model.entity.Category;
import model.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao = DaoFactory.getInstance().createCategoryDao();

    @Override
    public List<Category> findAllCategories() {
        return categoryDao.findAllCategories();
    }
}
