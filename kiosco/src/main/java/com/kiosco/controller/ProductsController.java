package com.kiosco.controller;

import an.awesome.pipelinr.Pipeline;

import com.kiosco.application.features.products.commands.CreateProductsCommand;
import com.kiosco.application.features.products.commands.DecreaseProductStockCommand;
import com.kiosco.application.features.products.commands.IncreaseProductStockCommand;
import com.kiosco.application.features.products.commands.UpdateProductsCommand;
import com.kiosco.application.features.products.queries.GetProductByStockQuery;
import com.kiosco.application.features.products.queries.GetProductsQuery;
import com.kiosco.domain.common.PaginationEntity;
import com.kiosco.domain.products.ProductEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "products", produces = "application/json")
public class ProductsController {

    @Autowired
    private Pipeline pipeline;

    @GetMapping
    public PaginationEntity<ProductEntity> get(@RequestParam Map<String, String> params) {
        var request = new GetProductsQuery();

        request.setOffset(Integer.parseInt(params.getOrDefault("offset", "1")));
        request.setLimit(Integer.parseInt(params.getOrDefault("limit", "10")));

        return this.pipeline.send(request);
    }

    @PostMapping
    public ProductEntity create(@RequestBody CreateProductsCommand product) {

        return this.pipeline.send(product);
    }

    @PutMapping("{id}")
    public ProductEntity update(@PathVariable("id") Long id, @RequestBody UpdateProductsCommand updateProduct) {
        updateProduct.setId(id);
        return this.pipeline.send(updateProduct);
    }


    @PostMapping("{id}/stock/increase")
    public ProductEntity increaseStock(@PathVariable Long id, @Valid @RequestBody IncreaseProductStockCommand body) {
        body.setProductId(id);
        return this.pipeline.send(body);
    }

    @PostMapping("{id}/stock/decrease")
    public ProductEntity decreaseStock(@PathVariable Long id, @Valid @RequestBody DecreaseProductStockCommand body) {
        body.setProductId(id);
        return this.pipeline.send(body);
    }

    @GetMapping("/stockMaxten")
    public PaginationEntity<ProductEntity> getProductsByStock(@RequestParam Map<String, String> params) {
        var request = new GetProductByStockQuery();

        request.setStock(Integer.parseInt(params.getOrDefault("stock", "5")));
        request.setOffset(Integer.parseInt(params.getOrDefault("offset", "1")));
        request.setLimit(Integer.parseInt(params.getOrDefault("limit", "10")));

        return this.pipeline.send(request);
    }
}
