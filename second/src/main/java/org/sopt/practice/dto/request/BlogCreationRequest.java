package org.sopt.practice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BlogCreationRequest(
        @NotBlank String title,
        @NotNull String description
) {
}
