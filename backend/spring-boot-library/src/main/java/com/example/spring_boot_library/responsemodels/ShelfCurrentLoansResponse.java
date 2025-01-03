package com.example.spring_boot_library.responsemodels;

import com.example.spring_boot_library.entity.Book;
import lombok.Data;

@Data
public class ShelfCurrentLoansResponse {

    public ShelfCurrentLoansResponse(Book book, int daysLeft) {
        this.book = book;
        this.daysLeft = daysLeft;
    }

    private Book book ;
    private int daysLeft;
}