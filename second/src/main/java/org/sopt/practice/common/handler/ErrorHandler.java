package org.sopt.practice.common.handler;

import lombok.val;
import org.sopt.practice.common.dto.ErrorResponse;
import org.sopt.practice.exception.BlogException;
import org.sopt.practice.exception.MemberException;
import org.sopt.practice.exception.PostingException;
import org.sopt.practice.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> memberException(MemberException exception) {
        val errorMessage = exception.getErrorMessage();
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(ErrorResponse.of(errorMessage.getMessage()));
    }

    @ExceptionHandler(BlogException.class)
    public ResponseEntity<ErrorResponse> blogException(BlogException exception) {
        val errorMessage = exception.getErrorMessage();
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(ErrorResponse.of(errorMessage.getMessage()));
    }

    @ExceptionHandler(PostingException.class)
    public ResponseEntity<ErrorResponse> postingException(PostingException exception) {
        val errorMessage = exception.getErrorMessage();
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(ErrorResponse.of(errorMessage.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(getErrorMessage(exception)));
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ErrorResponse> unauthorizedException(UnauthorizedException exception) {
        val errorMessage = exception.getErrorMessage();
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(ErrorResponse.of(errorMessage.getMessage()));
    }

    private String getErrorMessage(MethodArgumentNotValidException exception) {
        return Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();
    }
}
