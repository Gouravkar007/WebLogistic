package com.Parthi.Logistic.Product.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Parthi.Logistic.Product.model.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, String> {

    Optional<Product> findById(String id);

    

}