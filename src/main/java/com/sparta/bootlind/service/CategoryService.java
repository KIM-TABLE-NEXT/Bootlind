package com.sparta.bootlind.service;

import com.sparta.bootlind.dto.requestDto.CategoryRequest;
import com.sparta.bootlind.dto.requestDto.UpdateCategoryRequest;
import com.sparta.bootlind.entity.Category;
import com.sparta.bootlind.entity.User;
import com.sparta.bootlind.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public List<String> getCategoryList(User user) {
        if(!user.getStatus().equals("ACTIVATED"))
            throw new IllegalArgumentException("활성화 상태인 사용자만 가능합니다.");

        List<Category> categories = categoryRepository.findAll();

        List<String> categoryList = new ArrayList<>();
        for(Category category : categories)
            categoryList.add(category.getCategory());
        return categoryList;
    }

    public String addCategory(CategoryRequest request, User user) {
        if(categoryRepository.findByCategory(request.getCategory()).isPresent())
            throw new IllegalArgumentException("해당 카테고리가 존재합니다.");

       Category category =  categoryRepository.save(new Category(request.getCategory()));
       return request.getCategory() + " 가 추가되었습니다";
    }

    @Transactional
    public String updateCategory(UpdateCategoryRequest request, User user) {
        Category category =(Category) categoryRepository.findByCategory(request.getOldcategory()).orElseThrow(
                ()-> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
        );
        category.update(request.getNewcategory());
        return request.getOldcategory() + "에서 " + request.getNewcategory() + " 으로 변경되었습니다.";
    }


    public String deleteCategory(CategoryRequest request, User user) {
        Category category =(Category) categoryRepository.findByCategory(request.getCategory()).orElseThrow(
                ()-> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
        );
        categoryRepository.deleteById(category.getId());
        return request.getCategory() + " 이 삭제되었습니다.";
    }
}
