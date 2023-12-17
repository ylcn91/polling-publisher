package com.doksanbir.pollingpublisher.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private Set<Long> inventoryIds;
}
