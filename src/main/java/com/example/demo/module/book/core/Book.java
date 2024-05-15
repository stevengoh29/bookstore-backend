package com.example.demo.module.book.core;

import com.example.demo.module.book.category.BookCategory;
import com.example.demo.module.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    UUID id;
    String bookName;
    String bookDescription;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    User bookAuthor;
    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Collection<BookCategory> bookCategories;
    String bookPhotoPath;
    String createdBy;
    LocalDateTime createdAt = LocalDateTime.now();
    String updatedBy;
    LocalDateTime updatedAt;

    public Book(String bookName, String bookDescription, User bookAuthor, Collection<BookCategory> bookCategories, String bookPhotoPath, String createdBy) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookAuthor = bookAuthor;
        this.bookCategories = bookCategories;
        this.bookPhotoPath = bookPhotoPath;
        this.createdBy = createdBy;
    }

    public Book(String bookName, String bookDescription, User bookAuthor, Collection<BookCategory> bookCategories, String bookPhotoPath) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookAuthor = bookAuthor;
        this.bookCategories = bookCategories;
        this.bookPhotoPath = bookPhotoPath;
    }

    public Book(String bookName, String bookDescription, User bookAuthor) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookAuthor = bookAuthor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public User getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(User bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Collection<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(Collection<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public String getBookPhotoPath() {
        return bookPhotoPath;
    }

    public void setBookPhotoPath(String bookPhotoPath) {
        this.bookPhotoPath = bookPhotoPath;
    }
}
