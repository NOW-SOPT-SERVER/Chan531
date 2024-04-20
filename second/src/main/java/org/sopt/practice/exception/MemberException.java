package org.sopt.practice.exception;

import lombok.Getter;
import org.sopt.practice.message.ErrorMessage;

@Getter
public class MemberException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public MemberException(ErrorMessage errorMessage) {
        super("[MemberException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
