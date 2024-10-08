package com.github.runthebank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private Integer errorCode;
    private String message;
    private Object data;
}