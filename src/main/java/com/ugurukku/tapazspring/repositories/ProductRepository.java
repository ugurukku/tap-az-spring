package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


}
