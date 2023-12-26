package com.assignment.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.java.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
