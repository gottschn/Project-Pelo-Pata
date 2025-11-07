package com.kiosco.infrastructure.features.products.dto;

import com.kiosco.domain.products.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "PRODUCTOS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    public ProductEntity toDomain(){
        var entity = new ProductEntity();
        entity.setId(this.id);
        entity.setCode(this.codigo);
        entity.setName(this.nombre);
        entity.setPrice(this.precio);
        entity.setActive(this.activo);
        entity.setStock(this.stock);

        return entity;
    }
}
