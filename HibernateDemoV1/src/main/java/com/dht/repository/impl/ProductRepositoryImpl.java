/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository.impl;

import com.dht.hibernatedemov1.HibernateUtils;
import com.dht.pojo.Product;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class ProductRepositoryImpl {
    private static final int PAGE_SIZE = 6;
    
    public List<Product> getProducts(Map<String, String> params) {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            Root root = q.from(Product.class);
            q.select(root);
            
            if (params != null) {
                List<Predicate> predicates = new ArrayList<>();
                
                String kw = params.get("kw");
                if (kw != null && !kw.isEmpty()) {
                    predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
                }
                
                String fromPrice = params.get("fromPrice");
                if (fromPrice != null && !fromPrice.isEmpty()) {
                    predicates.add(b.greaterThanOrEqualTo(root.get("price"), 
                            fromPrice));
                }
                
                String toPrice = params.get("toPrice");
                if (toPrice != null && !toPrice.isEmpty()) {
                    predicates.add(b.lessThanOrEqualTo(root.get("price"), 
                            toPrice));
                }
                
                String cateId = params.get("categoryId");
                if (cateId != null && !cateId.isEmpty()) {
                    predicates.add(b.equal(root.get("categoryId").as(Integer.class), 
                            cateId));
                }
                
                q.where(predicates.toArray(Predicate[]::new));
            }
                
            Query query = s.createQuery(q);
            
            if (params != null) {
                int page = Integer.parseInt(params.getOrDefault("page", "1"));
                int start = (page - 1) * PAGE_SIZE;
                
                query.setFirstResult(start);
                query.setMaxResults(PAGE_SIZE);
            }
            
            return query.getResultList();
        }
    }
    
    public Product saveOrUpdate(Product p) {
         try (Session s = HibernateUtils.getFACTORY().openSession()) {
             if (p.getId() == null)
                 s.persist(s);
             else
                 s.merge(p);
         }
         
         return p;
    }
    
    public Product getProductById(int id) {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {
            return s.get(Product.class, id);
        }
    }
}
