package com.example.bookclub.book;

import com.example.bookclub.book.Book;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book); // 도서 등록
    Optional<Book> findById(Long bookId); // 도서 단건 조회
    void delete(Book book); // 도서 단건 삭제
    List<Book> findByNewBooks(LocalDate startDate, LocalDate endDate); // 신간 도서 조회
    List<Book> findByKeyword(String keyword); // 도서 검색 조회
    Book findByIsbn(Long isbn); // isbn 중복 검사

}
