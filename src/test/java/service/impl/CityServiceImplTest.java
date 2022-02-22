package service.impl;

import model.entity.City;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CityServiceImplTest {

    private CityServiceImpl cityService;
    public static final String CITY = "Kyiv";
    public static final int ID = 1;

    @Before
    public void setUp() { cityService = new CityServiceImpl();}

    @Test
    public void findAllCities(){
        List<City> result = cityService.findAllCities();
        assertNotNull(result);
    }

    @Test
    public void getCityNameById(){
        String result = cityService.getCityNameById(ID);
        assertEquals(CITY, result);
    }

}