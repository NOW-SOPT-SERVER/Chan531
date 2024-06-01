package org.sopt.practice.common.redis;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 1000L * 14)
public class Token {

    @Id
    private Long id;

    @Indexed
    private String refreshToken;

    public static Token of(final Long id, final String refreshToken) {
        return Token.builder()
                .id(id)
                .refreshToken(refreshToken)
                .build();
    }
}
