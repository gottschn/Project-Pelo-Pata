package com.kiosco.infrastructure.features.products.repository;

import com.kiosco.infrastructure.features.products.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductDto, Long>{

    //Query personalizada.
    @Query("select p from ProductDto p where p.codigo = :code")
    Optional<ProductDto> findByCode(@Param("code") String code);
}
