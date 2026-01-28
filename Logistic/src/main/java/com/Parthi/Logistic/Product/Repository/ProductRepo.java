package com.parthi.logistic.product.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parthi.logistic.product.model.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, String> {

    Optional<Product> findById(String id);

    

}