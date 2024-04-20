package org.sopt.practice.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.practice.domain.Member;

@Builder(access = AccessLevel.PRIVATE)
public record MemberCreationResponse(
        long id
) {

    public static MemberCreationResponse of(Member member) {
        return MemberCreationResponse.builder()
                .id(member.getId())
                .build();
    }
}
