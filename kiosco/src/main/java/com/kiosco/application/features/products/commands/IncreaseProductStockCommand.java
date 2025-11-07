package com.kiosco.application.features.products.commands;

import an.awesome.pipelinr.Command;
import com.kiosco.domain.products.ProductEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncreaseProductStockCommand implements Command<ProductEntity> {
    @NotNull
    private Long productId;
    @Min(1)
    private int quantity;
}
