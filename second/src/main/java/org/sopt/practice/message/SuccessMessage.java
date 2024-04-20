package org.sopt.practice.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_MEMBER("멤버 생성 성공"),
    SUCCESS_GET_MEMBER("멤버 조회 성공"),
    SUCCESS_DELETE_MEMBER("멤버 삭제 성공"),
    SUCCESS_GET_MEMBER_LIST("멤버 리스트 조회 성공"),
    ;

    private final String message;
}
