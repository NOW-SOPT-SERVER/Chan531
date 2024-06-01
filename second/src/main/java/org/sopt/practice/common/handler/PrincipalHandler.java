package org.sopt.practice.common.handler;

import lombok.val;
import org.sopt.practice.exception.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static org.sopt.practice.common.message.ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION;

@Component
public class PrincipalHandler {

    private static final String ANONYMOUS_USER = "anonymousUser";

    public Long getMemberIdFromPrincipal() {
        val principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        checkPrincipalNull(principal);
        return Long.valueOf(principal.toString());
    }

    public void checkPrincipalNull(final Object principal) {
        if (principal.toString().equals(ANONYMOUS_USER)) {
            throw new UnauthorizedException(JWT_UNAUTHORIZED_EXCEPTION);
        }
    }
}
