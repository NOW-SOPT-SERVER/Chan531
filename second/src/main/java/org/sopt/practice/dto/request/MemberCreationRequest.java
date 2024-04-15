package org.sopt.practice.dto.request;

import lombok.NonNull;
import org.sopt.practice.domain.Part;

public record MemberCreationRequest(
    @NonNull String name,
    @NonNull Part part,
    @NonNull int age
) {
}
