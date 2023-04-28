package org.parrolabs.api.products.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.parrolabs.api.model.ordersproducts.OrdersProducts;
import org.parrolabs.api.model.products.Products;
import org.parrolabs.api.repository.ordersproducts.OrdersProductsRepository;
import org.parrolabs.api.repository.products.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private OrdersProductsRepository ordersProductsRepository;

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products getProductById(Long id) {
        return productsRepository.findById(id).get();
    }

    public Products saveProduct(Products products) {
        Products newProduct = new Products(-1L,
                products.getDescription(),
                products.getPrice(),
                products.getWeight());
        return productsRepository.save(newProduct);
    }

    public Products updateProduct(Long id, Products products) {

        if (!productsRepository.existsById(id)) {
            throw new EntityNotFoundException("No Product found by id: " + id);
        }
        products.setId(id);
        return productsRepository.save(products);
    }

    public Boolean deleteProduct(Long id) throws Exception {

        List<OrdersProducts> orders = ordersProductsRepository.findByProductId(id);

        if (!orders.isEmpty()) {
            throw new Exception("Product can´t be deleted because has been used in an order.");
        }

        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
