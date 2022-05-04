package com.projects.catalog.repository;


import com.projects.catalog.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    Set<ItemEntity> findAllByProductIdIn(Set<Integer> productIds);

}
