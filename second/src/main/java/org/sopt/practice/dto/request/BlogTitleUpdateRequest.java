package org.sopt.practice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record BlogTitleUpdateRequest(
        @NotEmpty
        @Size(max = 30, message = "블로그 제목이 최대 글자 수(5자)를 초과했습니다.")
        String title
) {
}
