package com.kiosco.domain.products;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ProductEntity {
    private Long id;
    private String name;
    private String code;
    private Double price;
    private Boolean active;

}
