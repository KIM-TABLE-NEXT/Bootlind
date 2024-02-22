package com.sparta.bootlind.service;

import com.sparta.bootlind.dto.requestDto.CategoryRequest;
import com.sparta.bootlind.dto.requestDto.PostRequest;
import com.sparta.bootlind.dto.responseDto.PostResponse;
import com.sparta.bootlind.entity.Category;
import com.sparta.bootlind.entity.Post;
import com.sparta.bootlind.entity.User;
import com.sparta.bootlind.repository.CategoryRepository;
import com.sparta.bootlind.repository.CommentRepository;
import com.sparta.bootlind.repository.PostRepository;
import com.sparta.bootlind.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CommentRepository commentRepository;

    @Test
    @DisplayName("게시글 수정 테스트")
    void test1(){
        //given
        Long postId = 100L;
        User user = new User();
        user.setStatus("ACTIVATED");
        user.setUsername("Test");
        String oldTitle = "old title";
        String oldContent = "old content";
        String category = "category";
        String newTitle = "new title";
        String newContent = "new content";
        PostRequest oldPost = new PostRequest(oldTitle, oldContent, category);
        PostRequest newPost = new PostRequest(newTitle, newContent, category);
        Category testCategory = new Category(category);
        Post post = new Post(oldPost, user);
        PostService postService = new PostService(postRepository, userRepository, categoryRepository, commentRepository);
        given(postRepository.findById(postId)).willReturn(Optional.of(post));
        given(categoryRepository.findByCategory(category)).willReturn(Optional.of(testCategory));

        //when
        PostResponse result = postService.updatePost(postId, newPost, user);

        //then
        assertEquals(newTitle, result.getTitle());
        assertEquals(newContent, result.getContent());
        assertEquals(category, result.getCategory());
    }

    @Test
    @DisplayName("비활성 상태 테스트")
    void test2(){
        //given
        Long postId = 200L;
        User user = new User();
        user.setStatus("DEACTIVATED");
        user.setUsername("Test");
        String oldTitle = "old title";
        String oldContent = "old content";
        String category = "category";
        String newTitle = "new title";
        String newContent = "new content";
        PostRequest oldPost = new PostRequest(oldTitle, oldContent, category);
        PostRequest newPost = new PostRequest(newTitle, newContent, category);
        Category testCategory = new Category(category);
        Post post = new Post(oldPost, user);
        PostService postService = new PostService(postRepository, userRepository, categoryRepository, commentRepository);

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            postService.updatePost(postId, newPost, user);
                });

        //then
        assertEquals("활성화 상태인 사용자만 가능합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("수정 권한 테스트")
    void test3(){
        //given
        Long postId = 300L;
        User user = new User();
        user.setStatus("ACTIVATED");
        user.setUsername("Test");
        User nextUser = new User();
        nextUser.setStatus("ACTIVATED");
        nextUser.setUsername("Test2");
        String oldTitle = "old title";
        String oldContent = "old content";
        String category = "category";
        String newTitle = "new title";
        String newContent = "new content";
        PostRequest oldPost = new PostRequest(oldTitle, oldContent, category);
        PostRequest newPost = new PostRequest(newTitle, newContent, category);
        Category testCategory = new Category(category);
        Post post = new Post(oldPost, user);
        PostService postService = new PostService(postRepository, userRepository, categoryRepository, commentRepository);
        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            postService.updatePost(postId, newPost, nextUser);
        });

        //then
        assertEquals("게시글을 수정할 권한이 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("카테고리 테스트")
    void test4(){
        //given
        Long postId = 100L;
        User user = new User();
        user.setStatus("ACTIVATED");
        user.setUsername("Test");
        String oldTitle = "old title";
        String oldContent = "old content";
        String oldCategory = "old category";
        String newTitle = "new title";
        String newContent = "new content";
        String newCategory = "new Category";
        PostRequest oldPost = new PostRequest(oldTitle, oldContent, oldCategory);
        PostRequest newPost = new PostRequest(newTitle, newContent, newCategory);
        Category testCategory = new Category(oldCategory);
        Post post = new Post(oldPost, user);
        PostService postService = new PostService(postRepository, userRepository, categoryRepository, commentRepository);
        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            postService.updatePost(postId, newPost, user);
        });

        //then
        assertEquals("해당 카테고리가 존재하지 않습니다", exception.getMessage());
    }
}