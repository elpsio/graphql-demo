package com.projects.catalog.model;

import com.projects.catalog.util.MonetaryAmount;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private Integer id;
    private String itemId;
    private MonetaryAmount price;
    private String size;
    private Integer product;

}
