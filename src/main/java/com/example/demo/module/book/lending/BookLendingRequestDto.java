package com.example.demo.module.book.lending;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookLendingRequestDto(
        UUID bookId,
        UUID lenderId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String status,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy
) {
    public BookLendingRequestDto withCreatedBy(UUID createdBy) {
        return new BookLendingRequestDto(
                this.bookId(),
                this.lenderId(),
                this.startDate(),
                this.endDate(),
                this.status(),
                this.createdAt(),
                createdBy,
                this.updatedAt(),
                this.updatedBy()
        );
    }

    public BookLendingRequestDto withUpdatedBy(UUID updatedBy) {
        return new BookLendingRequestDto(
                this.bookId(),
                this.lenderId(),
                this.startDate(),
                this.endDate(),
                this.status(),
                this.createdAt(),
                this.createdBy(),
                this.updatedAt(),
                updatedBy
        );
    }
}
