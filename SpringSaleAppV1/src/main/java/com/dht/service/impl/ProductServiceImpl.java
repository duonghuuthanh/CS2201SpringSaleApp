/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.service.impl;

import com.dht.pojo.Product;
import com.dht.repository.ProductRepository;
import com.dht.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository prodRepo;

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        return this.prodRepo.getProducts(params);
    }

    @Override
    public Product saveOrUpdate(Product p) {
        p.setImage("https://res.cloudinary.com/dxxwcby8l/image/upload/v1710141425/bufd2mlepddhu4mh53ev.png");
        return this.prodRepo.saveOrUpdate(p);
    }

    @Override
    public Product getProductById(int id) {
        return this.prodRepo.getProductById(id);
    }
    
}
