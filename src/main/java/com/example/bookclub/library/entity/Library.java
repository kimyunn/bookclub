package com.example.bookclub.library.entity;

import com.example.bookclub.book.entity.Book;
import com.example.bookclub.common.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Entity
public class Library extends BaseTimeEntity {
    // 내서재 식별 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 도서 id
    @ManyToOne
    @JoinColumn(name = "book_Id")
    private Book bookId;
    // 회원 id
    @Column(nullable = false)
    private Long userId;

    public Library() {
    }

    @Builder
    public Library(Long id, Book bookId, Long userId) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
    }

    public static Library createLibrary(Long userId,Book book) {
        return Library.builder()
                .bookId(book)
                .userId(userId)
                .build();
    }

}
