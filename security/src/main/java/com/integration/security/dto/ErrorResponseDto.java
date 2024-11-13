package com.integration.security.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

    private String message;
}
