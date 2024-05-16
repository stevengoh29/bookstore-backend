package com.example.demo.module.book.category;

import java.util.Optional;
import java.util.UUID;

public class BookCategoryRequestDto {
    String categoryName;
    String categoryDescription;
    String username;
    UUID bookCategoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(UUID bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
