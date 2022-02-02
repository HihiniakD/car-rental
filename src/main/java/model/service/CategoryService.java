package model.service;

import model.entity.Brand;
import model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
}
