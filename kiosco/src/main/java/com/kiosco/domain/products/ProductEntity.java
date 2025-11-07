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
    private Integer stock;
    private Boolean active;


    public void increaseStock(Integer quantity) {
        if (this.stock == null) {
            this.stock = 0;
        }
        this.stock += quantity;
    }

    public void decreaseStock(Integer quantity) {
        if (this.stock == null || this.stock < quantity) {
            throw new IllegalStateException("INSUFFICIENT_STOCK");
        }
        this.stock -= quantity;
    }
}
