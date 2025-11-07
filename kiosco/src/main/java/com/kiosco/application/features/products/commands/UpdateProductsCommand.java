package com.kiosco.application.features.products.commands;

import an.awesome.pipelinr.Command;

import com.kiosco.domain.products.ProductEntity;
import lombok.Data;

@Data
public class UpdateProductsCommand implements Command<ProductEntity> {

    private Long id;
    private String name;
    private String code;
    private Double price;
    private Integer stock;

}
