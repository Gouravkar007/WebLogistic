package com.parthi.logistic.product.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parthi.logistic.common.exceptions.AlreadyExistsException;
import com.parthi.logistic.common.exceptions.BadRequestException;
import com.parthi.logistic.common.exceptions.InternalException;
import com.parthi.logistic.common.model.ErrorResponse;
import com.parthi.logistic.common.model.Properties;
import com.parthi.logistic.common.model.SuccessResponse;
import com.parthi.logistic.common.model.TransactionCategory;
import com.parthi.logistic.common.model.TransactionType;
import com.parthi.logistic.product.Repository.ProductRepo;
import com.parthi.logistic.product.model.Product;
import com.parthi.logistic.transaction.model.Transaction;
import com.parthi.logistic.transaction.service.TransactionService;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepo productRepo;
    TransactionService transactionService;

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
            if (productId == null || productId.trim().length() == 0) {

                throw new BadRequestException("Product id cannot be blank ");
            }
            Optional<Product> productQuary = productRepo.findById(productId);
            if (productQuary.isPresent()) {
                logger.info("Retrived product with productid [" + productId + "] from Database");
                return productQuary.get();
            } else {
                logger.warn("Product not found at index={}", productId);
                return null;
            }

        } catch (BadRequestException e) {
            logger.error("get product failed: " + e.getLocalizedMessage());
            throw new BadRequestException(e.getLocalizedMessage());

        } catch (RuntimeException e) {
            logger.error("Exception while fetching product at index={}", productId, e);
            throw new InternalException(e.getLocalizedMessage());
        }
    }

    /**
     * This method take updated product details and update the product in the List.
     * 
     * @param Product product details that need to be updated
     * @param int index position of the product to be updated
     * @return Returns the choice of the user
     */
    public SuccessResponse addProduct(Product product) {

        logger.info("ProductService.addProduct | id={}", product.getId());
        SuccessResponse successResponse = new SuccessResponse();

        try {
            //Check if the product exists
            
            Optional <Product> queryOutput = productRepo.findById(product.getId());
            if( queryOutput.isPresent()){
                throw new AlreadyExistsException("Product with ID "+product.getId()+" Already exists");
            }

            productRepo.save(product);
            successResponse.setMessage("Product added successfully with product id: "+product.getId());
            logger.info(successResponse.getMessage());
            return successResponse;

        } catch (AlreadyExistsException e) {
            logger.error("Add product failed: "+e.getLocalizedMessage());
            throw new AlreadyExistsException(e.getLocalizedMessage());
        } catch (RuntimeException e) {
            logger.error("Add product failed: "+e.getLocalizedMessage());
            throw new InternalException(e.getLocalizedMessage());
        }
    }

    /**
     * This method take updated product details and update the product in the List.
     * 
     * @param Product product details that need to be updated
     * @param int index position of the product to be updated
     * @return Returns the choice of the user
     */
    public SuccessResponse updateSoldProduct(String productId, LocalDate chekoutDate, Double sellingPrice)
            throws Exception {
        logger.info("ProductService.updateSoldProduct | id={}", productId);
        SuccessResponse successResponse = new SuccessResponse();
        try {

            Optional<Product> optionalProduct = productRepo.findById(productId);
            if (optionalProduct == null) {
                throw new Exception("Product with id " + productId + " not found. Entry Failed");
            }
            Product product = optionalProduct.get();
            if (!product.getStatus().equalsIgnoreCase(Properties.STATUS_AVAILABLE)) {
                throw new Exception("Product with id " + productId + " is " + product.getStatus() + ". Entry Failed");
            }
            product.setStockOutDate(chekoutDate);
            product.setSellingPrice(sellingPrice);
            product.setStatus(Properties.STATUS_SOLD);
            productRepo.save(product);
            successResponse.setMessage("Product staus updated");

            return successResponse;
        } catch (Exception ex) {
            logger.error("Update product failed: " + ex.getMessage());
            throw ex;
        }

       
    }

    public String returnToVendor(String id, LocalDate stockoutDate, String paymentMode) {
        String response = "";
        try {
            // fetch product
            Product product = getProduct(id);

            if (product != null) {
                product.setStatus(Properties.STATUS_RETURNED);
                product.setStockOutDate(stockoutDate);
                productRepo.save(product);
                response = "Product returned successfully";

                Transaction transaction = new Transaction();
                transaction.setAmount(product.getCostPrice());
                transaction.setDescription("Product with id " + product.getId() + " returned to vendor");
                transaction.setParticular(product.getId());
                transaction.setTxnCategory(TransactionCategory.PRODUCT_RETURN.toString());
                transaction.setTxnType(TransactionType.CREDIT.toString());
                transaction.setPaymentMode(paymentMode);
                transaction.setTransactionDate(stockoutDate);
                response = response + "\n" + transactionService.addTransaction(transaction);

                logger.info(response);
            } else {
                response = "Product with id " + id + " not found";
                logger.warn(response);
            }

        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.error("Exception in returnToVendor: " + response);
        } catch (Exception e) {
            response = e.getLocalizedMessage();
            logger.error("Exception occured while adding transaction: " + e.getLocalizedMessage());
        }

        return response;
    }



    // public String updateProduct(Product product, int index) {

    // logger.info("ENTER -> ProductService.updateProduct | id={}, index={}",
    // product.getId(), index);

    // try {
    // List<Product> products = new ArrayList<>();
    // productRepo.findAll().forEach(products::add);
    // logger.debug("Product updated in repository | id={}", product.getId());
    // } catch (RuntimeException e) {
    // logger.error("Exception occurred while updating product | id={}",
    // product.getId(), e);
    // return "Failed to update product";
    // }

    // logger.info("EXIT <- ProductService.updateProduct | id={}", product.getId());
    // return "Product update successfully";
    // }

}
