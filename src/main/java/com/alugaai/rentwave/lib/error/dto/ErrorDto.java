package com.alugaai.rentwave.lib.error.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDto {
    private String payload;
    private String message;
}
