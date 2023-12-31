package com.example.bookclub.book.dto.response;

import com.example.bookclub.book.entity.Book;
import lombok.*;

@Getter
public class BookMultipleResponse {
    private String title;
    private String author;
    private String translator;
    private String publisher;
    private String coverImageUrl;

    public BookMultipleResponse(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.translator = book.getTranslator();
        this.publisher = book.getPublisher();
        this.coverImageUrl = book.getCoverImageUrl();
    }
}

