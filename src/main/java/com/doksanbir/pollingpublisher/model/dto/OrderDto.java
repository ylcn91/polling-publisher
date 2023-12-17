package com.doksanbir.pollingpublisher.model.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId; // Reference to the user
    private Long productId; // Reference to the product
    private Integer quantity;
}
