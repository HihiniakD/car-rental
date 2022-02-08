package controller.command.impl.admin;

import controller.command.Command;
import model.entity.Brand;
import model.entity.Category;
import model.entity.City;
import service.BrandService;
import service.CategoryService;
import service.CityService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.Constants.*;
import static controller.Path.*;

public class AddCarCommand implements Command {

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
        return CAR_ADD_VIEW;
    }
}
