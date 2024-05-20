package com.example.demo.module.book.lending;

import com.example.demo.module.book.core.Book;
import com.example.demo.module.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "book_lending")
public class BookLending {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    Book bookId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lender_id")
    User lenderId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String status = "REQUESTED";
    UUID createdBy;
    LocalDateTime createdAt = LocalDateTime.now();
    UUID updatedBy;
    LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public User getLenderId() {
        return lenderId;
    }

    public void setLenderId(User lenderId) {
        this.lenderId = lenderId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BookLending(Book bookId, User lenderId, LocalDateTime startDate, LocalDateTime endDate, UUID createdBy) {
        this.bookId = bookId;
        this.lenderId = lenderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
    }

    public BookLending(UUID id, Book bookId, User lenderId, LocalDateTime startDate, LocalDateTime endDate, String status, UUID updatedBy, LocalDateTime updatedAt) {
        this.id = id;
        this.bookId = bookId;
        this.lenderId = lenderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
