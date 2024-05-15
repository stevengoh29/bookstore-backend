package com.example.demo.module.book.core;

import com.example.demo.exception.data.CategoryNotFoundException;
import com.example.demo.exception.data.DataNotFoundException;
import com.example.demo.module.book.category.BookCategory;
import com.example.demo.module.book.category.BookCategoryRepository;
import com.example.demo.module.user.User;
import com.example.demo.module.user.UserRepository;
import com.example.demo.util.CrudStatus;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    public List<Book> getBooks () {
        return bookRepository.findAll();
    }

    public Book getBookById (UUID bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public Book addBook (BookRequestDto payload) {
        try {
            System.out.println(payload);
            if(payload.bookAuthorId() == null) throw new RuntimeException("Book Author id is not in the payload");

            User user = userRepository.findById(payload.bookAuthorId()).orElse(null);
            if(user == null) throw new DataNotFoundException("User data is not found.");

            List<BookCategory> bookCategories = bookCategoryRepository.findByIdIn(payload.bookCategoriesId());
            if(bookCategories.size() != payload.bookCategoriesId().size()) throw new CategoryNotFoundException("Category is not found. Please check the categories Id in request.");

            Book book = new Book(payload.bookName(), payload.bookDescription(), user, bookCategories, null, payload.createdBy());

            return bookRepository.save(book);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    public Book editBookById (UUID bookId, BookRequestDto payload) {
        return null;
    }


}
