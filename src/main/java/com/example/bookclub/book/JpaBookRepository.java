package com.example.bookclub.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long>, BookRepository {

    @Override
    @Query("SELECT b FROM Book b WHERE b.publicationDate BETWEEN :startDate AND :endDate")
    List<Book> findByNewBooks(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Override
    //@Query("SELECT b FROM book b WHERE b.title LIKE %:keyword% OR b.author LIKE %:keyword% OR b.translator LIKE %:keyword%")
    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', :keyword, '%') OR b.author LIKE CONCAT('%', :keyword, '%') OR b.translator LIKE CONCAT('%', :keyword, '%')")
    List<Book> findByKeyword(@Param("keyword") String keyword);

}
