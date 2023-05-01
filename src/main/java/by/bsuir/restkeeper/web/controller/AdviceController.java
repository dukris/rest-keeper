package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.exception.*;
import by.bsuir.restkeeper.web.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleIOException(IOException ex) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(IllegalActionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleIllegalActionException(IllegalActionException ex) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleInvalidPasswordException(InvalidPasswordException ex) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn(ex.getMessage(), ex);
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> ExceptionDto.builder()
                        .field(error.getObjectName() + "." + error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto handleAccessDeniedException(AccessDeniedException ex) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleMailException(MailException ex) {
        log.error(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleStorageException(StorageException ex) {
        log.error(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleOtherExceptions(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .message("Please, try later!")
                .build();
    }

}
