package com.projects.catalog.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    private Integer id;
    private String name;
    private Integer parent;
    private List<Integer> children;
    private List<Integer> products;

}
