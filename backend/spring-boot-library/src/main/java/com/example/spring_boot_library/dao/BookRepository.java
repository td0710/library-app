package com.example.spring_boot_library.dao;

import com.example.spring_boot_library.entity.Book;
import jdk.jfr.Registered;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);

    @Query(value ="SELECT o FROM Book as o where o.id in:book_ids",nativeQuery = false)
    List<Book> findBooksByBookIds(@Param("book_ids") List<Long> book_ids);

}