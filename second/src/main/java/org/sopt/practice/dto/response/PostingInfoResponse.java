package org.sopt.practice.dto.response;

import lombok.Builder;
import lombok.NonNull;
import org.sopt.practice.domain.Posting;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record PostingInfoResponse(
        @NonNull String title,
        @NonNull String content
) {

    public static PostingInfoResponse of(Posting posting) {
        return PostingInfoResponse.builder()
                .title(posting.getTitle())
                .content(posting.getContent())
                .build();
    }
}
