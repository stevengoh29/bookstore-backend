package com.example.demo.module.book.category;

import com.example.demo.exception.security.AuthenticationException;
import com.example.demo.module.common.JwtService;
import com.example.demo.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book-category")
public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private JwtService jwtService;

    @PostMapping()
    public ResponseEntity<?> addBookCategory(@RequestBody BookCategoryRequestDto payload, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromTokenHeader(token);
        if(username.isEmpty()) throw new AuthenticationException("No user found from the token.");
        payload.setUsername(username);

        BookCategory bookCategory = bookCategoryService.addBookCategory(payload);
        return ResponseEntity.ok(bookCategory);
    }
}
