package service.impl;

import dao.CategoryDao;
import dao.factory.DaoFactory;
import model.entity.Category;
import service.CategoryService;

import java.util.List;

/**
 * Implementation of CategoryService
 */
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao = DaoFactory.getInstance().createCategoryDao();

    @Override
    public List<Category> findAllCategories() {
        return categoryDao.findAllCategories();
    }
}
