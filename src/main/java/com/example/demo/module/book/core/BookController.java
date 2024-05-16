package com.example.demo.module.book.core;

import com.example.demo.module.common.JwtService;
import com.example.demo.util.CrudStatus;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private JwtService jwtService;

    @GetMapping()
    public ResponseEntity<?> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable("bookId")UUID bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @PostMapping()
    public ResponseEntity<?> addBookCategory(@RequestBody BookRequestDto payload, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromTokenHeader(token);
        payload.withCreatedBy(username);

        Book book = bookService.addBook(payload);
        return ResponseEntity.ok(book);
    }

    @PutMapping("{bookId}")
    public ResponseEntity<?> editBookById(@PathVariable("bookId") UUID bookId, @RequestBody BookRequestDto payload, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromTokenHeader(token);
        payload.withUpdatedBy(username);

        Book book = bookService.editBookById(bookId, payload);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteBookById(@PathVariable("bookId") UUID bookId, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromTokenHeader(token);

        CrudStatus book = bookService.deleteBookById(bookId, username);
        return ResponseEntity.ok(book);
    }
}
