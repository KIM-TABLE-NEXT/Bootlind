package com.sparta.bootlind.dto.requestDto;

import com.sparta.bootlind.entity.CategoryEnum;
import lombok.Getter;

@Getter
public class PostRequest {
    private String title;
    private String content;
    private String category;
}
