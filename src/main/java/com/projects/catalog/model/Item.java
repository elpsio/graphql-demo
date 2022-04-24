package com.projects.catalog.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private Integer id;
    private String itemId;
    private String price;
    private String currency;
    private String size;
    private Product product;

}
