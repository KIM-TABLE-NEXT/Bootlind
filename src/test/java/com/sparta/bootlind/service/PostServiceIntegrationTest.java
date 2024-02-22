package com.sparta.bootlind.service;

import com.sparta.bootlind.dto.requestDto.PostRequest;
import com.sparta.bootlind.dto.responseDto.PostResponse;
import com.sparta.bootlind.entity.User;
import com.sparta.bootlind.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostServiceIntegrationTest {

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;


    User user;
    PostResponse createdPost = null;

    @Test
    @Order(1)
    @DisplayName("게시글 작성")
    void test1(){
        //given
        String title = "title";
        String content = "content";
        String category = "css";

        PostRequest postRequest = new PostRequest(title,content,category);
        user = userRepository.findById(1L).orElse(null);

        //when
        PostResponse postResponse = postService.createPost(postRequest, user);

        //then
        assertNotNull(postResponse.getId());
        assertEquals(title, postResponse.getTitle());
        assertEquals(content, postResponse.getContent());
        assertEquals(category, postResponse.getCategory());
        createdPost = postResponse;
    }

    @Test
    @Order(2)
    @DisplayName("게시글 수정")
    void test2(){
        //given
        Long postId = this.createdPost.getId();
        String newTitle = "new title";
        String newContent = "new content";
        String category = "css";
        PostRequest newPost = new PostRequest(newTitle, newContent, category);
        user = userRepository.findById(1L).orElse(null);
        //when
        PostResponse postResponse = postService.updatePost(postId, newPost, user);

        //then
        assertNotNull(postResponse.getId());
        assertEquals(newTitle, postResponse.getTitle());
        assertEquals(newContent, postResponse.getContent());
        assertEquals(category, postResponse.getCategory());
        createdPost = postResponse;
    }
}
