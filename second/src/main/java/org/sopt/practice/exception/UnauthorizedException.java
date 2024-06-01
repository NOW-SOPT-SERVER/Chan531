package org.sopt.practice.exception;

import lombok.Getter;
import org.sopt.practice.common.message.ErrorMessage;

@Getter
public class UnauthorizedException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public UnauthorizedException(ErrorMessage errorMessage) {
        super("[UnauthorizedException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
