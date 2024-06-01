package org.sopt.practice.common.redis;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.dto.response.TokenCreationResponse;
import org.sopt.practice.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.practice.common.message.ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenService {

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 5 * 1000L;

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    public TokenCreationResponse reissueToken(String refreshToken) {
        val token = findToken(refreshToken.substring("Bearer ".length()));
        val accessToken = jwtTokenProvider.createToken(token.getId(), ACCESS_TOKEN_EXPIRATION_TIME);
        return TokenCreationResponse.of(accessToken);
    }

    private Token findToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new UnauthorizedException(JWT_UNAUTHORIZED_EXCEPTION));
    }
}
