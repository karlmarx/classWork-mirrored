/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.service;

import com.karlmarxindustries.flooring.dto.Product;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author karlmarx
 */
class FlooringPDaoStubImpl {
    Product firstProduct;
    Product secondProduct;
    Map<String, Product> orders = new HashMap<>();

    
    public FlooringPDaoStubImpl(Product firstProduct, Product secondProduct) {
        this.firstProduct = firstProduct;
        this.secondProduct = secondProduct;
    }

    public Product getFirstProduct() {
        return firstProduct;
    }

    public void setFirstProduct(Product firstProduct) {
        this.firstProduct = firstProduct;
    }

    public Product getSecondProduct() {
        return secondProduct;
    }

    public void setSecondProduct(Product secondProduct) {
        this.secondProduct = secondProduct;
    }

    public Map<String, Product> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, Product> orders) {
        this.orders = orders;
    }
    
    
}
