package com.example.demo.module.book.lending;

import com.example.demo.module.common.JwtService;
import com.example.demo.util.CrudStatus;
import com.example.demo.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book-lending")
public class BookLendingController {
    @Autowired
    private BookLendingService bookLendingService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ResponseTemplate<?> responseTemplate;

    @GetMapping()
    public ResponseEntity<?> getBookLending () {
        List<BookLending> bookLendingList = bookLendingService.getBookLendings();
        return ResponseEntity.ok(responseTemplate.build(200, "Success", bookLendingList));
    }

    @GetMapping("{lendingId}")
    public ResponseEntity<?> getBookLendingById(@PathVariable("lendingId")UUID lendingId) {
        BookLending bookLending = bookLendingService.getBookLendingById(lendingId);
        return ResponseEntity.ok(responseTemplate.build(200, "Success", bookLending));
    }

    @PostMapping()
    public ResponseEntity<?> addBookLending (@RequestBody BookLendingRequestDto bookLendingRequestDto, @RequestHeader("Authorization") String token) {
        UUID createdBy = UUID.fromString(jwtService.getUsernameFromTokenHeader(token));
        bookLendingRequestDto.withCreatedBy(createdBy);

        BookLending bookLending = bookLendingService.addBookLending(bookLendingRequestDto);
        return ResponseEntity.ok(responseTemplate.build(200, "Success", bookLending));
    }

    @PatchMapping("{lendingId}/{status}")
    public ResponseEntity<?> processBookLending (@PathVariable("lendingId") UUID lendingId, @PathVariable("status") String status, @RequestHeader("Authorization") String token) {
        UUID updatedBy = UUID.fromString(jwtService.getUsernameFromTokenHeader(token));
        BookLending bookLending = bookLendingService.processBookLending(lendingId, status, updatedBy);

        return ResponseEntity.ok(responseTemplate.build(200, "Success", bookLending));
    }

    @PutMapping("{lendingId}")
    public ResponseEntity<?> editBookLendingById (@PathVariable("lendingId") UUID lendingId, @RequestBody BookLendingRequestDto payload, @RequestHeader("Authorization") String token) {
        UUID createdBy = UUID.fromString(jwtService.getUsernameFromTokenHeader(token));
        payload.withUpdatedBy(createdBy);

        BookLending bookLending = bookLendingService.editBookLendingById(lendingId, payload);
        return ResponseEntity.ok(responseTemplate.build(200, "Success", bookLending));
    }

    @DeleteMapping("{lendingId}")
    public ResponseEntity<?> deleteBookLendingById (@PathVariable("lendingId") UUID lendingId) {
        CrudStatus status = bookLendingService.deleteBookLendingById(lendingId);
        return ResponseEntity.ok(responseTemplate.build(200, "Success", status));
    }
}
