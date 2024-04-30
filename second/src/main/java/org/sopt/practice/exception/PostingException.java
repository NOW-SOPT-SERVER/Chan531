package org.sopt.practice.exception;

import lombok.Getter;
import org.sopt.practice.common.message.ErrorMessage;

@Getter
public class PostingException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public PostingException(ErrorMessage errorMessage) {
        super("[PostingException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
