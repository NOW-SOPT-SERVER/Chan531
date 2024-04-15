package org.sopt.practice.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.practice.domain.Member;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MemberListResponse(
        List<MemberProfileResponse> memberProfiles
) {

    public static MemberListResponse of(List<Member> members) {
        return MemberListResponse.builder()
                .memberProfiles(members.stream().map(MemberProfileResponse::of).toList())
                .build();
    }
}
