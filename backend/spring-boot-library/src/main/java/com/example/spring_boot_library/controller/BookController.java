package com.example.spring_boot_library.controller;


import com.example.spring_boot_library.entity.Book;
import com.example.spring_boot_library.service.BookService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/secure/currentloans/count")
    public int getCurrentLoansCount() {
        String userEmail = "user2@gmail.com";
        return bookService.currentLoansCount(userEmail);
    }
    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId) throws Exception {
        String userEmail = "user2@gmail.com";
        return bookService.checkoutBookByUser(userEmail,bookId) ;
    }


    @PutMapping("/secure/checkout")
    public Book chekoutBook (@RequestParam Long bookId) throws Exception {
        String userEmail = "user2@gmail.com";
        return bookService.checkOutBook(userEmail, bookId);
    }
}
