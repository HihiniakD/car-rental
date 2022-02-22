package model.service.impl;

import model.entity.Brand;
import org.junit.Before;
import org.junit.Test;
import service.impl.BrandServiceImpl;


import java.util.List;

import static org.junit.Assert.*;


public class BrandServiceImplTest {

    private BrandServiceImpl brandService;

    @Before
    public void setUp() { brandService = new BrandServiceImpl();}

    @Test
    public void findAllBrands(){
        List<Brand> result = brandService.findAllBrands();
        assertNotNull(result);
    }

}