package com.projects.catalog.repository;


import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
