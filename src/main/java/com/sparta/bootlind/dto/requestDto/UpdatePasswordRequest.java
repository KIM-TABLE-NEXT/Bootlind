package com.sparta.bootlind.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdatePasswordRequest {
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*.])(?=.*\\d)[a-zA-Z!@#$%^&*.\\d].{8,15}$",
            message = "비밀번호는 최소 7자 이상, 15자 이하로 알파벳과 특수문자, 숫자로 구성되어야 합니다.")
    private String oldpassword;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*.])(?=.*\\d)[a-zA-Z!@#$%^&*.\\d].{8,15}$",
            message = "비밀번호는 최소 7자 이상, 15자 이하로 알파벳과 특수문자, 숫자로 구성되어야 합니다.")
    private String newpassword;

}

