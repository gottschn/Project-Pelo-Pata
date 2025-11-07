package com.kiosco.application.features.products.queries;

import an.awesome.pipelinr.Command;
import com.kiosco.domain.common.PaginationEntity;
import com.kiosco.domain.products.ProductEntity;
import lombok.Data;

@Data
public class GetProductByStockQuery implements Command<PaginationEntity<ProductEntity>> {
    private Integer offset;
    private Integer limit;
    private Integer stock;
}
