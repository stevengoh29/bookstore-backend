package com.example.demo.module.book.category;

import com.example.demo.module.common.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookCategoryService {
    private static final Logger log = LoggerFactory.getLogger(BookCategoryService.class);
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private JwtService jwtService;

    public List<BookCategory> getBookCategories () {
        return bookCategoryRepository.findAll();
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
}
