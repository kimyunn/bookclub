package com.example.bookclub.book;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookRequestDto {
    private String title;
    private String author;
    private String translator;
    private String publisher;
    private LocalDate publicationDate;
    private String description;
    private String contents;
    private String coverImageUrl;
    private Long isbn;

    @Builder
    public BookRequestDto(String title, String author, String translator,
                          String publisher, LocalDate publicationDate,
                          String description, String contents, String coverImageUrl, Long isbn) {
        this.title = title;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.description = description;
        this.contents = contents;
        this.coverImageUrl = coverImageUrl;
        this.isbn = isbn;
    }

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .translator(translator)
                .publisher(publisher)
                .publicationDate(publicationDate)
                .description(description)
                .contents(contents)
                .coverImageUrl(coverImageUrl)
                .isbn(isbn)
                .build();
    }
}
