package com.Parthi.Logistic.Product.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Parthi.Logistic.Product.Repository.ProductRepo;
import com.Parthi.Logistic.Product.model.Product;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepo productRepo;

    public List<Product> getProducts() {

        logger.info("ProductService.getProducts");

        List<Product> products = new ArrayList<>();

        try {
            productRepo.findAll().forEach(products::add);
            logger.debug("Total products fetched={}", products.size());
        } catch (RuntimeException e) {
            logger.error("Exception occurred while fetching product list", e);
        }

        logger.info("ProductService.getProducts");
        return products;
    }

    /**
     * This method take a an procuct id and retrieve the product from the List.
     * 
     * @param int index with which the product needs to be found
     * @return Returns the product
     */
    public Product getProduct(String productId) {

        logger.info("ProductService.getProduct | index={}", productId);

        Product product = null;
        try {
            Optional<Product> productQuary = productRepo.findById(productId);
            if (productQuary.isPresent()) {
                logger.info("Retrived product with productid [" + productId + "] from Database");
                return productQuary.get();
            } else {
                logger.warn("Product not found at index={}", productId);
                return null;
            }
           
        } catch (RuntimeException e) {
            logger.error("Exception while fetching product at index={}", productId, e);
        }

        logger.info("ProductService.getProduct | index={}", productId);
        return product;
    }

    /**
     * This method take updated product details and update the product in the List.
     * 
     * @param Product product details that need to be updated
     * @param int     index position of the product to be updated
     * @return Returns the choice of the user
     */
    public String addProduct(Product product) {

        logger.info("ProductService.addProduct | id={}", product.getId());

        try {
            productRepo.save(product);
            logger.debug("Product added to repository | id={}", product.getId());
             

        } catch (RuntimeException e) {
            logger.error("Exception occurred while adding product | id={}",
                    product.getId(), e);
            return "Failed to add product";
        }

        logger.info(" ProductService.addProduct | id={}", product.getId());
        return "Product added successfully";
    }

    /**
     * This method take updated product details and update the product in the List.
     * 
     * @param Product product details that need to be updated
     * @param int     index position of the product to be updated
     * @return Returns the choice of the user
     */
    public String updateProduct(Product product, int index) {

        logger.info("ENTER -> ProductService.updateProduct | id={}, index={}",
                product.getId(), index);

        try {
             List<Product> products = new ArrayList<>();
            productRepo.findAll().forEach(products::add);
            logger.debug("Product updated in repository | id={}", product.getId());
        } catch (RuntimeException e) {
            logger.error("Exception occurred while updating product | id={}",
                    product.getId(), e);
            return "Failed to update product";
        }

        logger.info("EXIT  <- ProductService.updateProduct | id={}", product.getId());
        return "Product update successfully";
    }
    

}
