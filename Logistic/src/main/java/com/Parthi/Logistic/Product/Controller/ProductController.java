package com.parthi.logistic.product.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.parthi.logistic.common.model.SuccessResponse;
import com.parthi.logistic.product.Service.ProductService;
import com.parthi.logistic.product.model.Product;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProductController {

    static final Logger logger = LoggerFactory.getLogger(ProductController.class);

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
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts() {

        logger.info("Request recived from all products");
        List<Product> products = productService.getProducts();

        logger.info("Product recived successfully");
        return new ResponseEntity<>(products, HttpStatus.OK);
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
    @ResponseBody
    public ResponseEntity<Product> getProductById(
            @PathVariable String productId) {

        logger.info("Request received for retrieve product");

        Product product = productService.getProduct(productId);

        logger.info("Product recived successfully");
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    /**
     * This method takes product details from the request body and
     * adds the product to the system.
     *
     * @param product Product details that need to be added
     * @return Returns the status message of the add operation
     */
    @PostMapping("/product/add")
    @ResponseBody
    public ResponseEntity<SuccessResponse> addProduct(@Valid @RequestBody Product product) {
        logger.info("Request received for Add product");
        SuccessResponse response = productService.addProduct(product);
        logger.info("Product added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * This method takes updated product details and updates
     * an existing product in the system.
     *
     * @param product Product details that need to be updated
     * @return Returns the status message of the update operation
     * @throws Exception
     */
    // @PostMapping("/product/update")
    // public ResponseEntity<SuccessResponse> updateProduct(@Valid @RequestBody
    // String productId, LocalDate chekoutDate,
    // Double sellingPrice) throws Exception {

    // String response =

    // if ("Product not found".equals(response)) {
    // return ResponseEntity
    // .status(HttpStatus.NOT_FOUND)
    // .body(response);
    // }

    // if ("Failed to update product".equals(response)) {
    // return ResponseEntity
    // .status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body(response);
    // }

    // return ResponseEntity
    // .status(HttpStatus.OK)
    // .body(response);
    // }

    @PostMapping("/product/return/vendor/{productId}")
    @ResponseBody
    public ResponseEntity<String> returnToVendor(
            @PathVariable String productId,
            @RequestParam String stockoutDate,
            @RequestParam String paymentMode) throws Exception {

        logger.info("Request received for return to vendor | id={}", productId);

        LocalDate date = LocalDate.parse(stockoutDate);

        String response = productService.returnToVendor(productId, date, paymentMode);

        logger.info("Return to vendor response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
