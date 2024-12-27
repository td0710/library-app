package com.example.spring_boot_library.controller;


import com.example.spring_boot_library.entity.Book;
import com.example.spring_boot_library.service.BookService;
import com.example.spring_boot_library.utils.ExtractJWT;
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
    public int getCurrentLoansCount(@RequestHeader(value = "Authorization") String token) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"") ;
        return bookService.currentLoansCount(userEmail);
    }
    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token,
            @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"") ;
        return bookService.checkoutBookByUser(userEmail,bookId) ;
    }


    @PutMapping("/secure/checkout")
    public Book chekoutBook (@RequestHeader(value = "Authorization") String token,
            @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"") ;
        return bookService.checkOutBook(userEmail, bookId);
    }
}
