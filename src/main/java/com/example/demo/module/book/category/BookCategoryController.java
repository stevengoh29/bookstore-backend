package com.example.demo.module.book.category;

import com.example.demo.exception.security.AuthenticationException;
import com.example.demo.module.common.JwtService;
import com.example.demo.util.CrudStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book-category")
public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private JwtService jwtService;

    @GetMapping()
    public ResponseEntity<?> getBookCategories() {
        List<BookCategory> bookCategories = bookCategoryService.getBookCategories();
        return ResponseEntity.ok(bookCategories);
    }

    @GetMapping("{bookCategoryId}")
    public ResponseEntity<?> getBookCategoryById(@PathVariable("bookCategoryId") UUID bookCategoryId) {
        BookCategory bookCategory = bookCategoryService.getBookCategoriesById(bookCategoryId);
        return ResponseEntity.ok(bookCategory);
    }

    @PostMapping()
    public ResponseEntity<?> addBookCategory(@RequestBody BookCategoryRequestDto payload, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromTokenHeader(token);
        if(username.isEmpty()) throw new AuthenticationException("No user found from the token.");
        payload.setUsername(username);

        BookCategory bookCategory = bookCategoryService.addBookCategory(payload);
        return ResponseEntity.ok(bookCategory);
    }

    @PutMapping("{bookCategoryId}")
    public ResponseEntity<?> editBookCategory(@PathVariable("bookCategoryId") UUID bookCategoryId, @RequestBody BookCategoryRequestDto payload, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromTokenHeader(token);
        if(username.isEmpty()) throw new AuthenticationException("No user found from the token.");
        payload.setBookCategoryId(bookCategoryId);
        payload.setUsername(username);

        BookCategory bookCategory = bookCategoryService.editBookCategory(payload);
        return ResponseEntity.ok(bookCategory);
    }

    @DeleteMapping("{bookCategoryId}")
    public ResponseEntity<?> deleteBookCategory(@PathVariable("bookCategoryId") UUID bookCategoryId, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromTokenHeader(token);
        if(username.isEmpty()) throw new AuthenticationException("No user found from the token.");

        CrudStatus status = bookCategoryService.deleteBooKCategory(bookCategoryId, username);
        return ResponseEntity.ok(status);
    }
}
