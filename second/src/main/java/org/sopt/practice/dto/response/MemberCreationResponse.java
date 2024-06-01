package org.sopt.practice.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record MemberCreationResponse(
        @NonNull String accessToken,
        @NonNull String refreshToken,
        @NonNull String userId
) {

    public static MemberCreationResponse of(String accessToken, String refreshToken, String userId) {
        return MemberCreationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userId)
                .build();
    }
}
