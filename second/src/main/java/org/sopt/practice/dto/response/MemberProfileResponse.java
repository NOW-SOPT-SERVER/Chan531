package org.sopt.practice.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import org.sopt.practice.domain.Member;
import org.sopt.practice.domain.Part;

@Builder(access = AccessLevel.PRIVATE)
public record MemberProfileResponse(
        long id,
        @NonNull String name,
        int age,
        @NonNull Part part
) {

    public static MemberProfileResponse of(Member member) {
        return MemberProfileResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .part(member.getPart())
                .build();
    }
}
