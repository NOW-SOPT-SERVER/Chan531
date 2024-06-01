package org.sopt.practice.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_MEMBER("멤버 생성 성공"),
    SUCCESS_GET_MEMBER("멤버 조회 성공"),
    SUCCESS_DELETE_MEMBER("멤버 삭제 성공"),
    SUCCESS_GET_MEMBER_LIST("멤버 리스트 조회 성공"),

    SUCCESS_CREATE_BLOG("블로그 생성 성공"),
    SUCCESS_UPDATE_TITLE("블로그 제목 수정 성공"),

    SUCCESS_CREATE_POSTING("게시글 생성 성공"),
    SUCCESS_GET_POSTING("게시글 조회 성공"),

    SUCCESS_REISSUE_TOKEN("토큰 재발급 성공"),
    ;

    private final String message;
}
