package com.example.demo.module.book.lending;

import com.example.demo.exception.data.BookNotFoundException;
import com.example.demo.exception.data.CategoryNotFoundException;
import com.example.demo.exception.data.DataNotFoundException;
import com.example.demo.module.book.category.BookCategory;
import com.example.demo.module.book.core.Book;
import com.example.demo.module.book.core.BookRepository;
import com.example.demo.module.book.core.BookRequestDto;
import com.example.demo.module.user.User;
import com.example.demo.module.user.UserRepository;
import com.example.demo.util.CrudStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookLendingService {
    private static final Logger log = LoggerFactory.getLogger(BookLendingService.class);
    @Autowired
    private BookLendingRepository bookLendingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<BookLending> getBookLendings() {
        List<BookLending> bookLendings = bookLendingRepository.findAll();
        log.info("Fetched {} book lending from repository", bookLendings.size());
        bookLendings.forEach(entity -> log.info("Book Lending fetched: {}", entity));
        return bookLendings;
    }

    public BookLending getBookLendingById (UUID bookLendingId) {
        BookLending bookLending = bookLendingRepository.findById(bookLendingId).orElse(null);
        log.info("Get Book Lending Success. Data: {}", bookLending);
        return bookLending;
    }

    public BookLending addBookLending (BookLendingRequestDto payload) {
        Book book = bookRepository.findById(payload.bookId()).orElse(null);
        if(book == null) throw new BookNotFoundException("Book is not found.");

        User userExist = userRepository.findById(payload.lenderId()).orElse(null);
        if(userExist == null) throw new DataNotFoundException("User is not found.");

        BookLending bookToInsert = new BookLending(book, userExist, payload.startDate(), payload.endDate(), payload.createdBy());
        BookLending bookLending = bookLendingRepository.save(bookToInsert);
        log.info("Add Book Lending Success. Data: {}", bookLending);
        return bookLending;
    }

    public BookLending editBookLendingById (UUID lendingId, BookLendingRequestDto payload) {
        Book book = bookRepository.findById(payload.bookId()).orElse(null);
        if(book == null) throw new BookNotFoundException("Book is not found. Please make sure the Id");

        User user = userRepository.findById(payload.lenderId()).orElse(null);
        if (user == null) throw new DataNotFoundException("User data is not found.");

        BookLending bookToUpdate = new BookLending(lendingId, book, user, payload.startDate(), payload.endDate(), payload.status(), payload.updatedBy(), LocalDateTime.now());
        BookLending update = bookLendingRepository.save(bookToUpdate);
        log.info("Edit Book Lending is successful. Data: {}", update);

        return update;
    }

    public CrudStatus deleteBookLendingById (UUID id) {
        bookLendingRepository.deleteById(id);
        return CrudStatus.SUCCESS;
    }

    public BookLending processBookLending(UUID lendingId, String status, UUID updatedBy) {
        BookLending bookLending = bookLendingRepository.findById(lendingId).orElse(null);
        if(bookLending == null) throw new DataNotFoundException("Book Lending Data is not found. Please make sure you have entered the right one.");

        bookLending.setStatus(status);
        bookLending.setUpdatedBy(updatedBy);
        bookLending.setUpdatedAt(LocalDateTime.now());

        BookLending result = bookLendingRepository.save(bookLending);
        log.info("Update Book Lending is successful. Data: {}", result);
        return result;
    }
}
