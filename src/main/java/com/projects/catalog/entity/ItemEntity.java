package com.projects.catalog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "itemId")
    private String itemId;
    @Column(name = "price")
    private String price;
    @Column(name = "currency")
    private String currency;
    @Column(name = "size")
    private String size;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

}
