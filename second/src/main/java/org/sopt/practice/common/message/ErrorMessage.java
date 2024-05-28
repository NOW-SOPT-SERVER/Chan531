package org.sopt.practice.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    NOT_MATCH_BLOG_POSTING(BAD_REQUEST, "블로그의 게시글이 아닙니다."),

    /* 403 FORBIDDEN : 권한 없음 */
    NOT_MATCH_MEMBER_BLOG(FORBIDDEN, "멤버의 블로그가 아닙니다."),

    /* 404 NOT_FOUND : 자원을 찾을 수 없음 */
    INVALID_MEMBER(NOT_FOUND, "유효하지 않은 회원입니다."),
    INVALID_BLOG(NOT_FOUND, "유효하지 않은 블로그입니다."),
    INVALID_POSTING(NOT_FOUND, "유효하지 않은 게시글입니다."),


    /* 409 CONFLICT : 중복된 자원 */
    EXIST_MEMBER(CONFLICT, "멤버가 이미 존재하는 게시물입니다."),
    EXIST_BLOG(CONFLICT, "게시물이 이미 존재하는 멤버입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
