package com.projects.catalog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "productId")
    private String productId;
    @Column(name = "description")
    private String description;
    @Column(name = "item_id")
    @ElementCollection
    private List<Integer> itemIds;
    @Column(name = "category_id")
    private Integer categoryId;

}
