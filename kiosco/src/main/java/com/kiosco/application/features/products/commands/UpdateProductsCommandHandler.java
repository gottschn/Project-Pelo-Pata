package com.kiosco.application.features.products.commands;


import an.awesome.pipelinr.Command;

import com.kiosco.domain.products.ProductEntity;
import com.kiosco.infrastructure.configuration.exceptionHandlers.throwable.BadRequestException;
import com.kiosco.infrastructure.features.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductsCommandHandler implements Command.Handler<UpdateProductsCommand, ProductEntity>{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductEntity handle(UpdateProductsCommand updateProductsCommand) {

       var updateProduct =  productRepository.findById(updateProductsCommand.getId()).orElseThrow(() -> new BadRequestException( "el id del producto es invalido"));


       updateProduct.setId(updateProductsCommand.getId());
       updateProduct.setNombre(updateProductsCommand.getName());
       updateProduct.setCodigo(updateProductsCommand.getCode());
       updateProduct.setPrecio(updateProductsCommand.getPrice());

        return this.productRepository.save(updateProduct).toDomain();
    }
}
