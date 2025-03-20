/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.controllers;

import com.dht.pojo.Product;
import com.dht.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author admin
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService prodSer;
    
    @GetMapping("/products")
    public String manageProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products";
    }
    
    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product p) {
        this.prodSer.saveOrUpdate(p);
        
        return "redirect:/";
    }
}
