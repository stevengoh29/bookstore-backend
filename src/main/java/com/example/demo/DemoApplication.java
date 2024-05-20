package com.example.demo;

import com.example.demo.module.book.category.BookCategory;
import com.example.demo.module.book.category.BookCategoryRequestDto;
import com.example.demo.module.book.category.BookCategoryService;
import com.example.demo.module.book.core.Book;
import com.example.demo.module.book.core.BookRequestDto;
import com.example.demo.module.book.core.BookService;
import com.example.demo.module.common.upload.UploadService;
import com.example.demo.module.user.User;
import com.example.demo.module.user.UserRequest;
import com.example.demo.module.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Resource
	UploadService uploadService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
    //uploadService.deleteAll();
		uploadService.init();
	}

	@Bean
	public CommandLineRunner initUser (UserService userService, PasswordEncoder passwordEncoder, BookCategoryService bookCategoryService, BookService bookService) {
		return args -> {
//			UserRequest request = new UserRequest("admin", passwordEncoder.encode("admin"), "Testing");
//			User user = userService.addUser(request);

//			BookCategoryRequestDto bookCategoryRequestDto = new BookCategoryRequestDto("Horror", "This is horror section", user.getId().toString());
//			BookCategory category = bookCategoryService.addBookCategory(bookCategoryRequestDto);
//
//			List<String> bookCategoryIds = new ArrayList<String>();
//			bookCategoryIds.add(category.getId().toString());
//
//			BookRequestDto bookRequestDto = new BookRequestDto("Conjuring", "The first series of Conjuring. The best horror book in the series", user.getId(), bookCategoryIds, null, user.getId().toString(), null);
//			bookService.addBook(bookRequestDto);
		};
	}
}