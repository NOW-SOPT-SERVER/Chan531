package org.sopt.practice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import org.sopt.practice.domain.Part;

public record MemberCreationRequest(
    @Size(max = 20, message = "이름이 상당하네요...")
    @NotBlank String name,
    @NonNull Part part,
    @Min(value = 18, message = "잼민이는 가라.")
    @Max(value = 120, message = "어르신...")
    int age
) {
}
