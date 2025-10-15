package com.kiosco.domain.common;

import lombok.Getter;

import java.util.List;

@Getter
public class PaginationEntity<T>{
    private List<T> rows;
    private Integer totalPage;
    private Integer currentPage;


    public PaginationEntity(List<T> rows, Integer currentPage, Integer totalPages) {
        this.totalPage = totalPages;
        this.rows = rows;
        this.currentPage = currentPage;
    }
}
