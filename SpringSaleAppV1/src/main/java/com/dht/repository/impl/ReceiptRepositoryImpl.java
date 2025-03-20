///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.dht.repository.impl;
//
//import com.dht.hibernatedemov1.HibernateUtils;
//import com.dht.pojo.Cart;
//import com.dht.pojo.OrderDetail;
//import com.dht.pojo.SaleOrder;
//import java.util.Date;
//import java.util.Map;
//import org.hibernate.Session;
//
///**
// *
// * @author admin
// */
//public class ReceiptRepositoryImpl {
//    public void addReceipt(Map<String, Cart> carts)  {
//        try (Session s = HibernateUtils.getFACTORY().openSession()) {
//            SaleOrder order = new SaleOrder();
//            order.setCreatedDate(new Date());
//            UserRepositoryImpl t = new UserRepositoryImpl();
//            order.setUserId(t.getUserByUsername("admin"));
//            s.persist(order);
//            
//            ProductRepositoryImpl pro = new ProductRepositoryImpl();
//            for (var cart: carts.values()) {
//                OrderDetail d = new OrderDetail();
//                d.setProductId(pro.getProductById(cart.getId()));
//                d.setQuantity(cart.getQuantity());
//                d.setUnitPrice(cart.getPrice());
//                
//                s.persist(d);
//            }
//        }
//    }
//            
//}
