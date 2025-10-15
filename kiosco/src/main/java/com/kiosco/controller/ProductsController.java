package com.kiosco.controller;

import an.awesome.pipelinr.Pipeline;

import com.kiosco.application.features.products.commands.CreateProductsCommand;
import com.kiosco.application.features.products.commands.UpdateProductsCommand;
import com.kiosco.application.features.products.queries.GetProductsQuery;
import com.kiosco.domain.common.PaginationEntity;
import com.kiosco.domain.products.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "products", produces="application/json")
public class ProductsController {

    @Autowired
    private Pipeline pipeline;

    @GetMapping
    public PaginationEntity<ProductEntity> get(@RequestParam Map<String, String> params){
        var request = new GetProductsQuery();

        request.setOffset(Integer.parseInt(params.getOrDefault("offset", "1")));
        request.setLimit(Integer.parseInt(params.getOrDefault("limit", "10")));

        return this.pipeline.send(request);
    }

    @PostMapping
    public ProductEntity create(@RequestBody CreateProductsCommand product){

        return this.pipeline.send(product);
    }

    @PutMapping("{id}")
    public ProductEntity update(@PathVariable("id") Long id, @RequestBody UpdateProductsCommand updateProduct){
        updateProduct.setId(id);
        return this.pipeline.send(updateProduct);
    }
}
