package com.example.bookclub.repository;

import com.example.bookclub.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long>, BookRepository {
    @Override
    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', :keyword, '%') OR b.author LIKE CONCAT('%', :keyword, '%') OR b.translator LIKE CONCAT('%', :keyword, '%')")
    Page<Book> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
