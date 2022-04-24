package com.projects.catalog.repository;


import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

}
