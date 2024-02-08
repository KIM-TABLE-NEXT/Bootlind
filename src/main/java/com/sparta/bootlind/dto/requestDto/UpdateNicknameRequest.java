package com.sparta.bootlind.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateNicknameRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9가-힣\\d]{2,10}$",
            message = "닉네임은 2~10자로 구성되어야 합니다.")
    private String nickname;
}
