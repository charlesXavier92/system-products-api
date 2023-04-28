package org.parrolabs.api.products.controller;

import java.util.List;

import org.parrolabs.api.model.products.Products;
import org.parrolabs.api.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    public static final String GET_PRODUCTS = "/getAll";
    public static final String GET_PRODUCTS_ID = "/getById/{id}";
    public static final String SAVE_PRODUCT = "/save";
    public static final String UPDATE_PRODUCT = "/update/{id}";
    public static final String DELETE_PRODUCT = "/delete/{id}";

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Return all products from data base", response = Products.class, notes = "General Service")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(GET_PRODUCTS)
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> result = null;
        try {
            result = productService.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Return the Product by Id", response = Products.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(GET_PRODUCTS_ID)
    public ResponseEntity<Products> getProductById(
            @PathVariable("id") Long id) {
        Products result = null;
        try {
            result = productService.getProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Create the Product", response = Products.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping (SAVE_PRODUCT)
    public ResponseEntity<Products> saveProduct(
            @RequestBody Products products) {
        Products result = null;
        try {
            result = productService.saveProduct(products);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update the Product", response = Products.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping (UPDATE_PRODUCT)
    public ResponseEntity<Products> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody Products products) {
        Products result = null;
        try {
            result = productService.updateProduct(id, products);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete the Product", response = Products.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping (DELETE_PRODUCT)
    public ResponseEntity<Boolean> deleteProduct(
            @PathVariable("id") Long id) {
        Boolean result = false;
        try {
            result = productService.deleteProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
