package com.doksanbir.pollingpublisher.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Set<Long> orderIds;
}
