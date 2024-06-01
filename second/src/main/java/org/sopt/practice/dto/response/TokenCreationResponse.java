package org.sopt.practice.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record TokenCreationResponse(
        @NonNull String accessToken
) {

    public static TokenCreationResponse of(String accessToken) {
        return TokenCreationResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
