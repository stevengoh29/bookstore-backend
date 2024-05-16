package com.example.demo.module.book.core;

import com.example.demo.module.book.category.BookCategory;
import com.example.demo.module.user.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public record BookRequestDto(
        String bookName,
        String bookDescription,
        UUID bookAuthorId,
        List<String> bookCategoriesId,
        String bookPhotoPath,
        String createdBy,
        String updatedBy
) {
    public BookRequestDto withCreatedBy(String createdBy) {
        return new BookRequestDto(
                this.bookName(),
                this.bookDescription(),
                this.bookAuthorId(),
                this.bookCategoriesId(),
                this.bookPhotoPath(),
                this.updatedBy(),
                createdBy // Set the createdBy field
        );
    }

    public BookRequestDto withUpdatedBy(String updatedBy) {
        return new BookRequestDto(
                this.bookName(),
                this.bookDescription(),
                this.bookAuthorId(),
                this.bookCategoriesId(),
                this.bookPhotoPath(),
                this.createdBy(),
                updatedBy
        );
    }
}