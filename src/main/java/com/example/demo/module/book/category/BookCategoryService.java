package com.example.demo.module.book.category;

import com.example.demo.exception.data.CategoryNotFoundException;
import com.example.demo.util.CrudStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookCategoryService {
    private static final Logger log = LoggerFactory.getLogger(BookCategoryService.class);

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    public List<BookCategory> getBookCategories () {
        List<BookCategory> bookCategoryList = bookCategoryRepository.findAll();

        // Log the result
        log.info("Fetched {} book category from repository", bookCategoryList.size());

        // Optionally, log each entity individually
        bookCategoryList.forEach(entity -> log.info("Book Category fetched: {}", entity));

        return bookCategoryList;
    }

    public BookCategory getBookCategoriesById (UUID id) {
        return bookCategoryRepository.findById(id).orElse(null);
    }

    public BookCategory addBookCategory (BookCategoryRequestDto payload) {
        BookCategory category = new BookCategory(payload.getCategoryName(), payload.getCategoryDescription());
        category.setCreatedBy(payload.getUsername());

        BookCategory result = bookCategoryRepository.save(category);
        log.info("Add Book Category is successful. Data: {}", result);

        return result;
    }

    public BookCategory editBookCategory (BookCategoryRequestDto payload) {
        BookCategory bookCategory = bookCategoryRepository.findById(payload.getBookCategoryId()).orElse(null);
        if(bookCategory == null) throw new CategoryNotFoundException("Book Category is not found.");

        BookCategory category = new BookCategory(payload.getCategoryName(), payload.getCategoryDescription());
        category.setUpdatedBy(payload.getUsername());
        category.setUpdatedAt(LocalDateTime.now());
        category.setId(bookCategory.id);

        BookCategory result = bookCategoryRepository.save(category);
        log.info("Edit Book Category is successful. Data: {}", result);

        return result;
    }

    public CrudStatus deleteBooKCategory (UUID bookCategoryId, String username) {
        BookCategory bookCategory = bookCategoryRepository.findById(bookCategoryId).orElse(null);
        if(bookCategory == null) throw new CategoryNotFoundException("Book Category is not found.");

        bookCategory.setDeleted(true);
        bookCategory.setUpdatedAt(LocalDateTime.now());
        bookCategory.setUpdatedBy(username);
        BookCategory result = bookCategoryRepository.save(bookCategory);
        log.info("Delete Book Category is successful. Data: {}", result);

        return CrudStatus.SUCCESS;
    }
}
