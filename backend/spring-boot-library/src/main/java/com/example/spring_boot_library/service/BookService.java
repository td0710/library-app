package com.example.spring_boot_library.service;


import com.example.spring_boot_library.dao.BookRepository;
import com.example.spring_boot_library.dao.CheckoutRepository;
import com.example.spring_boot_library.entity.Book;
import com.example.spring_boot_library.entity.Checkout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    private CheckoutRepository checkoutRepository;

    public BookService(BookRepository bookRepository, CheckoutRepository checkoutRepository) {
        this.bookRepository = bookRepository;
        this.checkoutRepository = checkoutRepository;
    }

    public Book checkOutBook(String userEmail, Long BookId) throws Exception {

        Optional<Book> book = bookRepository.findById(BookId);

        Checkout validateChekout = checkoutRepository.findByUserEmailAndBookId(userEmail, BookId);

        if (!book.isPresent() || validateChekout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't exist or already checked out by user");
        }
        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());
        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );
        checkoutRepository.save(checkout);
        return book.get();
    }

    public Boolean checkoutBookByUser(String userEmail, Long BookId) throws Exception {
        Checkout validateChekout = checkoutRepository.findByUserEmailAndBookId(userEmail, BookId);

        if (validateChekout != null) {
            return true;
        } else return false;
    }
    public int currentLoansCount(String userEmail)  {
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }
}
