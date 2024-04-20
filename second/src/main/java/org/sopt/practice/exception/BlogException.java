package org.sopt.practice.exception;

import lombok.Getter;
import org.sopt.practice.common.message.ErrorMessage;

@Getter
public class BlogException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public BlogException(ErrorMessage errorMessage) {
        super("[BlogException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
