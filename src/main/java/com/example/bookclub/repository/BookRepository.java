package com.example.bookclub.repository;

import com.example.bookclub.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Boolean existsByIsbn(Long isbn);
    Optional<Book> findById(Long bookId);
    void delete(Book book);
    Page<Book> findByPublicationDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Book> findByKeyword(String keyword, Pageable pageable);
}

