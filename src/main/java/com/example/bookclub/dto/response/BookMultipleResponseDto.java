package com.example.bookclub.dto.response;

import com.example.bookclub.domain.Book;
import lombok.*;

@Getter
public class BookMultipleResponseDto {
    private Long id;
    private String title;
    private String author;
    private String translator;
    private String publisher;
    private String coverImageUrl;

    public BookMultipleResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.translator = book.getTranslator();
        this.publisher = book.getPublisher();
        this.coverImageUrl = book.getCoverImageUrl();
    }
}

