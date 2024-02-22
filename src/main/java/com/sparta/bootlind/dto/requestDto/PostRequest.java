package com.sparta.bootlind.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private String category;
}
