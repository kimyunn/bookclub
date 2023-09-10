package com.example.bookclub.book.dto.response;

import com.example.bookclub.book.entity.Book;
import lombok.*;

@Getter
public class BookMultipleResponseDto {
    private String title;
    private String author;
    private String translator;
    private String publisher;
    private String coverImageUrl;

    public BookMultipleResponseDto(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.translator = book.getTranslator();
        this.publisher = book.getPublisher();
        this.coverImageUrl = book.getCoverImageUrl();
    }

}
