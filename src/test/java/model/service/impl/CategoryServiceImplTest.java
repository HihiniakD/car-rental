package model.service.impl;

import model.entity.Category;
import org.junit.Before;
import org.junit.Test;
import service.impl.CategoryServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;

    @Before
    public void setUp() { categoryService = new CategoryServiceImpl();}

    @Test
    public void findAllCategories(){
        List<Category> result = categoryService.findAllCategories();
        assertNotNull(result);
    }

}