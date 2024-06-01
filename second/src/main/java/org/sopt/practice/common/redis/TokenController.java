package org.sopt.practice.common.redis;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.common.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.sopt.practice.common.dto.SuccessResponse.*;
import static org.sopt.practice.common.message.SuccessMessage.SUCCESS_REISSUE_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tokens")
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("/reissue")
    public ResponseEntity<SuccessResponse> reissueToken(@RequestHeader("Authorization") String refreshToken) {
        val response = tokenService.reissueToken(refreshToken);
        return ResponseEntity.ok(of(SUCCESS_REISSUE_TOKEN.getMessage(), response));
    }
}
