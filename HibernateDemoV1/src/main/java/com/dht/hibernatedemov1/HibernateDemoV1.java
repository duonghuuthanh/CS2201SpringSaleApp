/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.dht.hibernatedemov1;

import com.dht.pojo.User;
import com.dht.repository.impl.CategoryRepositoryImpl;
import com.dht.repository.impl.ProductRepositoryImpl;
import com.dht.repository.impl.StatsRepositoryImpl;
import com.dht.repository.impl.UserRepositoryImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class HibernateDemoV1 {

    public static void main(String[] args) {
        UserRepositoryImpl s = new UserRepositoryImpl();
        User u = s.getUserByUsername("dhthanh");
        System.out.println(u.getFirstName());
        System.out.println(u.getLastName());
        
//        StatsRepositoryImpl s = new StatsRepositoryImpl();
//        s.statsRevenueByTime("QUARTER").forEach(o -> System.out.printf("%d: %d\n", o[0], o[1]));
//        CategoryRepositoryImpl s = new CategoryRepositoryImpl();
//        s.getCates().forEach(c -> System.out.println(c.getName()));
//        
//        ProductRepositoryImpl s2 = new ProductRepositoryImpl();
//        
//        Map<String, String> params = new HashMap<>();
//        params.put("page", "2");
////        params.put("kw", "iPhone");
////        params.put("fromPrice", "30000000");
////        params.put("categoryId", "2");
//        
//        s2.getProducts(params).forEach(p -> System.out.printf("%d - %s - %.1f\n", 
//                p.getId(), p.getName(), p.getPrice()));
    }
}
