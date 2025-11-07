package com.kiosco.application.features.products.commands;

import an.awesome.pipelinr.Command;
import com.kiosco.domain.products.ProductEntity;
import com.kiosco.infrastructure.configuration.exceptionHandlers.throwable.BadRequestException;
import com.kiosco.infrastructure.features.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DecreaseProductStockCommandHandler  implements Command.Handler<DecreaseProductStockCommand, ProductEntity> {
//    @Override
//    public ProductEntity handle(DecreaseProductStockCommand command) {
//        // Lógica para disminuir el stock del producto
//        // Por ejemplo, buscar el producto por ID, disminuir el stock y guardar los cambios
//        return null; // Retornar el producto actualizado
//    }

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductEntity handle(DecreaseProductStockCommand productEntity) {

        var productDecrease = productRepository.findById(productEntity.getProductId()).orElseThrow( () -> new BadRequestException("Product not found"));

        productDecrease.setStock(productDecrease.getStock() - productEntity.getQuantity());
        return this.productRepository.save(productDecrease).toDomain();
    }
}
