package com.example.spring_boot_library.service;

import com.example.spring_boot_library.dao.BookRepository;
import com.example.spring_boot_library.dao.CheckoutRepository;
import com.example.spring_boot_library.dao.ReviewRepository;
import com.example.spring_boot_library.entity.Book;
import com.example.spring_boot_library.requestmodels.AddBookRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private final CheckoutRepository checkoutRepository;
    private final ReviewRepository reviewRepository;
    private BookRepository bookRepository;

    public AdminService(BookRepository bookRepository, CheckoutRepository checkoutRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.checkoutRepository = checkoutRepository;
        this.reviewRepository = reviewRepository;
    }

    public void increaseBookQuantity(Long bookId) throws Exception {
        Optional<Book> book = bookRepository.findById(bookId);

        if(!book.isPresent()) {
            throw new Exception("Book not found");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);
        book.get().setCopies(book.get().getCopies() + 1);

        bookRepository.save(book.get());
    }

    public void decreaseBookQuantity(Long bookId) throws Exception {
        Optional<Book> book = bookRepository.findById(bookId);

        if(!book.isPresent()) {
            throw new Exception("Book not found");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        book.get().setCopies(book.get().getCopies() - 1);

        bookRepository.save(book.get());
    }

    public void postBook(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setDescription(addBookRequest.getDescription());
        book.setCopies(addBookRequest.getCopies());
        book.setCategory(addBookRequest.getCategory());
        book.setImg(addBookRequest.getImg());
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) throws Exception {
        Optional<Book> book = bookRepository.findById(bookId);

        if(!book.isPresent()) {
            throw new Exception("Book not found");
        }

        bookRepository.delete(book.get());
        checkoutRepository.deleteAllByBookId(bookId);
        reviewRepository.deleteAllByBookId(bookId);
    }
}
