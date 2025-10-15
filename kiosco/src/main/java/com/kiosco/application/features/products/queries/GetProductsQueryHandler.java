package com.kiosco.application.features.products.queries;

import an.awesome.pipelinr.Command;

import com.kiosco.domain.common.PaginationEntity;
import com.kiosco.domain.products.ProductEntity;
import com.kiosco.infrastructure.features.products.dto.ProductDto;
import com.kiosco.infrastructure.features.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetProductsQueryHandler  implements Command.Handler<GetProductsQuery,PaginationEntity<ProductEntity>>{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public PaginationEntity<ProductEntity> handle(GetProductsQuery command) {
        Pageable pageable = PageRequest.of(command.getOffset() - 1, command.getLimit());

        var data = this.productRepository.findAll(pageable);

        return new PaginationEntity<>(data.map(ProductDto::toDomain).getContent(), command.getOffset(), data.getTotalPages());
    }
}
