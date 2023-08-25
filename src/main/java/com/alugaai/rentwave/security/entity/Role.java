package com.alugaai.rentwave.security.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private Long id;
    private String title;
    private String description;
    private String access;
}
