package model.dao;

import model.entity.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category>{
    List<Category> findAllCategories();
}
