package com.sparta.bootlind.entity;

public enum CategoryEnum {
    SPRING(Category.SPRING),
    REACT(Category.REACT),
    NODE_JS(Category.NODE_JS);

    private final String category;

    CategoryEnum(String category){
        this.category = category;
    }

    public String getCategory(){
        return  this.category;
    }

    public static class Category{
        public static final String SPRING = "SPRING";
        public static final String REACT = "REACT";
        public static final String NODE_JS = "NODE_JS";
    }
}
