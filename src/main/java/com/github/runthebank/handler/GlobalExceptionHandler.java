package com.github.runthebank.handler;

import com.github.runthebank.dto.ErrorObjectDto;
import com.github.runthebank.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpServletRequest httpServletRequest;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<ErrorObjectDto> errors = getErrors(ex);
        ErrorResponseDto errorResponse = getErrorResponse(ex, errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ExceptionUtils.getRootCauseMessage(ex);

        if (ex instanceof HttpStatusCodeException httpError) {
            status = HttpStatus.valueOf(httpError.getStatusCode().value());
        }

        ErrorResponseDto errorResponse = new ErrorResponseDto(status.value(), message, null);

        log.error("Ocorreu o erro: {}", ex.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(403,
                String.format("Você não possui permissão para usar o recurso: %s ", httpServletRequest.getRequestURI()),
                null);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    private ErrorResponseDto getErrorResponse(MethodArgumentNotValidException ex, List<ErrorObjectDto> errors) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "Requisição possui campos inválidos", errors);
    }

    private List<ErrorObjectDto> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObjectDto(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }

}