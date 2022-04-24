package com.projects.catalog.repository;


import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
