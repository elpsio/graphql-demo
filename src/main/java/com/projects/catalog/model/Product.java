package com.projects.catalog.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Integer id;
    private String productId;
    private String description;
    private List<Integer> items;
    private Integer category;

}
