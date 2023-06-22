package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.exception.IllegalActionException;
import by.bsuir.restkeeper.domain.exception.InvalidPasswordException;
import by.bsuir.restkeeper.domain.exception.MailException;
import by.bsuir.restkeeper.domain.exception.ResourceAlreadyExistsException;
import by.bsuir.restkeeper.domain.exception.ResourceNotFoundException;
import by.bsuir.restkeeper.domain.exception.StorageException;
import by.bsuir.restkeeper.web.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
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

    /**
     * Method to handle ResourceNotFoundException.
     *
     * @param ex ResourceNotFoundException
     * @return ExceptionDto
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handle(
            final ResourceNotFoundException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle InternalAuthenticationServiceException.
     *
     * @param ex InternalAuthenticationServiceException
     * @return ExceptionDto
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handle(
            final InternalAuthenticationServiceException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle DisabledException.
     *
     * @param ex DisabledException
     * @return ExceptionDto
     */
    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final DisabledException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg("User isn't activated!")
                .build();
    }

    /**
     * Method to handle ResourceAlreadyExistsException.
     *
     * @param ex ResourceAlreadyExistsException
     * @return ExceptionDto
     */
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final ResourceAlreadyExistsException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle IOException.
     *
     * @param ex IOException
     * @return ExceptionDto
     */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final IOException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle IllegalActionException.
     *
     * @param ex IllegalActionException
     * @return ExceptionDto
     */
    @ExceptionHandler(IllegalActionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final IllegalActionException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle InvalidPasswordException.
     *
     * @param ex InvalidPasswordException
     * @return ExceptionDto
     */
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final InvalidPasswordException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle MethodArgumentNotValidException.
     *
     * @param ex MethodArgumentNotValidException
     * @return ExceptionDto
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionDto> handle(
            final MethodArgumentNotValidException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> ExceptionDto.builder()
                        .field(error.getObjectName() + "." + error.getField())
                        .msg(error.getDefaultMessage())
                        .build())
                .toList();
    }

    /**
     * Method to handle MethodArgumentTypeMismatchException.
     *
     * @param ex MethodArgumentTypeMismatchException
     * @return ExceptionDto
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final MethodArgumentTypeMismatchException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle BindException.
     *
     * @param ex BindException
     * @return ExceptionDto
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final BindException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle BadCredentialsException.
     *
     * @param ex BadCredentialsException
     * @return ExceptionDto
     */
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final BadCredentialsException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg("Invalid username or password!")
                .build();
    }

    /**
     * Method to handle AccessDeniedException.
     *
     * @param ex AccessDeniedException
     * @return ExceptionDto
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto handle(
            final AccessDeniedException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle HttpMessageNotReadableException.
     *
     * @param ex HttpMessageNotReadableException
     * @return ExceptionDto
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(
            final HttpMessageNotReadableException ex
    ) {
        log.warn(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle MailException.
     *
     * @param ex MailException
     * @return ExceptionDto
     */
    @ExceptionHandler(MailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handle(
            final MailException ex
    ) {
        log.error(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle StorageException.
     *
     * @param ex StorageException
     * @return ExceptionDto
     */
    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handle(
            final StorageException ex
    ) {
        log.error(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg(ex.getMessage())
                .build();
    }

    /**
     * Method to handle RException.
     *
     * @param ex Exception
     * @return ExceptionDto
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handle(
            final Exception ex
    ) {
        log.error(ex.getMessage(), ex);
        return ExceptionDto.builder()
                .msg("Please, try later!")
                .build();
    }

}
