package com.sparta.bootlind.repository;

import com.sparta.bootlind.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
