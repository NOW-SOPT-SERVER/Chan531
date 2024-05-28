package org.sopt.practice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostingCreationRequest(
        @NotBlank String title,
        @NotNull String content
) {
}
