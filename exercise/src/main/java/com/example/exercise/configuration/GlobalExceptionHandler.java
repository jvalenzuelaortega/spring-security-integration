package com.example.exercise.configuration;

import com.example.exercise.dto.ErrorResponseDto;
import com.example.exercise.exceptions.BadFormatException;
import com.example.exercise.exceptions.PhoneOperationException;
import com.example.exercise.exceptions.UserOperationException;
import io.jsonwebtoken.ExpiredJwtException;
import org.hibernate.query.QueryTypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserOperationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleUserOperationException(UserOperationException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler({PhoneOperationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handlePhoneOperationException(PhoneOperationException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDto> handleExpiredJwtException(ExpiredJwtException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(QueryTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleQueryTypeMismatchException(QueryTypeMismatchException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(BadFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleBadFormatException(BadFormatException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleResponseTypeValueException(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .build());
    }


}
