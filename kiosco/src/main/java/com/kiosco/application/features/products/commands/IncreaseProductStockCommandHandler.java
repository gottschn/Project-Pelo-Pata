package com.kiosco.application.features.products.commands;

import an.awesome.pipelinr.Command;
import com.kiosco.domain.products.ProductEntity;
import com.kiosco.infrastructure.configuration.exceptionHandlers.throwable.BadRequestException;
import com.kiosco.infrastructure.features.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class IncreaseProductStockCommandHandler implements Command.Handler<IncreaseProductStockCommand,ProductEntity>{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductEntity handle(IncreaseProductStockCommand increaseProductStockCommand) {
       var productIncrease = productRepository.findById(increaseProductStockCommand.getProductId())
               .orElseThrow(()-> new BadRequestException("Product not found"));

        productIncrease.setId(increaseProductStockCommand.getProductId());
        productIncrease.setStock(productIncrease.getStock() + increaseProductStockCommand.getQuantity());

        return this.productRepository.save(productIncrease).toDomain();
    }
}
