package com.sparta.bootlind.repository;

import com.sparta.bootlind.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Object> findByCategory(String category);
}
