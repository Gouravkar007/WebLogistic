package com.parthi.logistic.product.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.parthi.logistic.product.model.Product;
import com.parthi.logistic.product.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * This method retrieves all products from the system.
     * It calls the service layer to fetch product data from the database
     * and returns the result as a JSON array.
     *
     * @return Returns a list of products. If no products are found,
     *         an empty list is returned.
     */
    @GetMapping("/product/getall")
    public ResponseEntity<List<Product>> getProducts() {

        List<Product> products = productService.getProducts();

        if (products.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        return ResponseEntity.ok(products);
    }

    /**
     * This method retrieves a product based on the provided product id.
     * If the product is not found, it returns a NOT_FOUND response
     * with an appropriate message.
     *
     * @param id The unique identifier of the product
     * @return Returns the product if found, otherwise returns a not found message
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {

        Product product = productService.getProduct(productId);

        if (product == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Product with id " + productId + " not found");
        }

        return ResponseEntity.ok(product);
    }

    /**
     * This method takes product details from the request body and
     * adds the product to the system.
     *
     * @param product Product details that need to be added
     * @return Returns the status message of the add operation
     */
    @PostMapping("/product/add")
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {

        String response = productService.addProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    /**
     * This method takes updated product details and updates
     * an existing product in the system.
     *
     * @param product Product details that need to be updated
     * @return Returns the status message of the update operation
     */
    @PostMapping("/product/update")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody Product
    product) {

    String response = productService.updateProduct(product, 0);

    if ("Product not found".equals(response)) {
    return ResponseEntity
    .status(HttpStatus.NOT_FOUND)
    .body(response);
    }

    if ("Failed to update product".equals(response)) {
    return ResponseEntity
    .status(HttpStatus.INTERNAL_SERVER_ERROR)
    .body(response);
    }

    return ResponseEntity
    .status(HttpStatus.OK)
    .body(response);
    }

}
