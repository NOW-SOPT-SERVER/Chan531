package org.sopt.practice.common.handler;

import lombok.val;
import org.sopt.practice.common.dto.Response;
import org.sopt.practice.exception.MemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<Response> memberException(MemberException exception) {
        val errorMessage = exception.getErrorMessage();
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(Response.fail(errorMessage.getMessage()));
    }
}
