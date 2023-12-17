package com.doksanbir.pollingpublisher.model.dto;


import lombok.Data;

@Data
public class InventoryDto {
    private Long id;
    private Long productId; // Reference to the product
    private Integer quantity;
}
