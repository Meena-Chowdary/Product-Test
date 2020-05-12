package com.cts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Product;

@Repository
public interface ProductManagementRepository extends CrudRepository<Product, Integer> {

}