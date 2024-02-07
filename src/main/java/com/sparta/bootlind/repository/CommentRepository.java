package com.sparta.bootlind.repository;

import com.sparta.bootlind.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
