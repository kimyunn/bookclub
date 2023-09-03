package com.example.bookclub.book;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookSearchResultDto {
    private String title;
    private String author;
    private String translator;
    private String publisher;
    private String coverImageUrl;

    @Builder
    public BookSearchResultDto(String title, String author, String translator, String publisher, String coverImageUrl) {
        this.title = title;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.coverImageUrl = coverImageUrl;
    }
}
