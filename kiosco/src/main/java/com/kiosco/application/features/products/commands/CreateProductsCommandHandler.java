package com.kiosco.application.features.products.commands;

import an.awesome.pipelinr.Command;

import com.kiosco.domain.products.ProductEntity;
import com.kiosco.infrastructure.features.products.dto.ProductDto;
import com.kiosco.infrastructure.features.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProductsCommandHandler implements Command.Handler<CreateProductsCommand, ProductEntity>{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductEntity handle(CreateProductsCommand command) {


        productRepository.findByCode(command.getCode()).ifPresent(product -> {
            throw new IllegalStateException("Product already exists");
        });

        var createProduct =  ProductDto.builder()
                .nombre(command.getName())
                .codigo(command.getCode())
                .precio(command.getPrice())
                .stock(command.getStock())
                .activo(true)
                .build();


        var result = this.productRepository.save(createProduct);
        return result.toDomain();
    }
}
