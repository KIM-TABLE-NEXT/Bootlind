package com.sparta.bootlind.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCategoryRequest {
    @NotBlank
    private String oldcategory;
    @NotBlank
    private String newcategory;
}
