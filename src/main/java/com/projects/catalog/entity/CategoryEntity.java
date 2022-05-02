package com.projects.catalog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "parent")
    private Integer parent;
    @ElementCollection
    @CollectionTable(name = "children", joinColumns = @JoinColumn(name = "category_id"))
    private List<Integer> children;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ProductEntity> productEntityList;


}
