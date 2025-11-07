package com.kiosco.application.features.products.queries;

import an.awesome.pipelinr.Command;
import com.kiosco.domain.common.PaginationEntity;
import com.kiosco.domain.products.ProductEntity;
import com.kiosco.infrastructure.configuration.exceptionHandlers.throwable.BadRequestException;
import com.kiosco.infrastructure.features.products.dto.ProductDto;
import com.kiosco.infrastructure.features.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetProductsByStockQueryHandler implements Command.Handler<GetProductByStockQuery, PaginationEntity<ProductEntity>> {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public PaginationEntity<ProductEntity> handle(GetProductByStockQuery command) {

        Pageable pageable = PageRequest.of(command.getOffset() - 1, command.getLimit());

        // Obtener la página y mapear DTO -> domain
        Page<ProductEntity> pageRes = productRepository
                .findByStock(command.getStock(), pageable)
                .map(ProductDto::toDomain);

        if(pageRes.isEmpty()) throw new BadRequestException("No products found with the specified stock level");

        // Construir PaginationEntity con la lista de contenido, el offset (página) y totalPages
        return new PaginationEntity<>(pageRes.getContent(), command.getOffset(), pageRes.getTotalPages());

    }
}
