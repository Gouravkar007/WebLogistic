package com.parthi.logistic.product.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parthi.logistic.product.model.Product;

import java.util.Optional;


@Repository
public interface ProductRepo extends CrudRepository<Product, String> {

    //Product findById(Product product);
    Optional <Product> findById(String id);

    

}