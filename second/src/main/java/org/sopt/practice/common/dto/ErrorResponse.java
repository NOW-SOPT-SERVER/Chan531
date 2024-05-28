package org.sopt.practice.common.dto;

import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record ErrorResponse(
        boolean success,
        @NonNull String message
) {

    public static ErrorResponse of(String message) {
        return ErrorResponse.builder()
                .success(false)
                .message(message)
                .build();
    }
}
