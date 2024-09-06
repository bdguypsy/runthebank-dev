package com.github.runthebank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObjectDto {
    private String message;
    private String field;
    private Object parameter;
}