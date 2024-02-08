package com.sparta.bootlind.dto.responseDto;

import com.sparta.bootlind.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;

    public PostResponse(Post post, String nickname) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = nickname;
        this.createdAt = post.getCreatedAt();
    }
}
