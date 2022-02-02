package controller.command.impl;

import controller.command.Command;
import model.entity.Brand;
import model.entity.Category;
import model.entity.City;
import model.service.BrandService;
import model.service.CategoryService;
import model.service.CityService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.Constants.*;
import static controller.Path.INDEX_VIEW;

public class MainPageCommand implements Command {
    private final BrandService brandService = ServiceFactory.getBrandService();
    private final CityService cityService = ServiceFactory.getCityService();
    private final CategoryService categoryService = ServiceFactory.getCategoryService();



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<City> cities = cityService.findAllCities();
        List<Brand> brands = brandService.findAllBrands();
        List<Category> categories = categoryService.findAllCategories();
        request.setAttribute(CITIES_PARAMETER, cities);
        request.setAttribute(BRANDS_PARAMETER, brands);
        request.setAttribute(CATEGORIES_PARAMETER, categories);
        return INDEX_VIEW;
    }
}
