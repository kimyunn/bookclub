package com.example.bookclub.book.repository;

import com.example.bookclub.book.entity.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);
    Optional<Book> findById(Long bookId);
    void delete(Book book);
    List<Book> findByNewBooks(LocalDate startDate, LocalDate endDate);
    List<Book> findByKeyword(String keyword);
    Boolean existsByIsbn(Long isbn);


}