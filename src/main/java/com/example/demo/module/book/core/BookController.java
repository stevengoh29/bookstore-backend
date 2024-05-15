package com.example.demo.module.book.core;

import com.example.demo.module.common.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private JwtService jwtService;

    @PostMapping()
    public ResponseEntity<?> addBookCategory(@RequestBody BookRequestDto payload, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromTokenHeader(token);
        payload.withCreatedBy(username);

        Book book = bookService.addBook(payload);
        return ResponseEntity.ok(book);
    }
}
