package org.sopt.practice.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record SuccessResponse(
        boolean success,
        @NonNull String message,
        @JsonInclude(value = NON_NULL) Object data
) {

    public static SuccessResponse of(String message, Object data) {
        return SuccessResponse.builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static SuccessResponse of(String message) {
        return SuccessResponse.builder()
                .success(true)
                .message(message)
                .build();
    }
}
