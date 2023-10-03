package com.example.bookclub.book.dto.response;

import com.example.bookclub.book.entity.Book;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String translator;
    private String publisher;
    private LocalDate publicationDate;
    private String description;
    private String contents;
    private String coverImageUrl;
    private Long isbn;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public BookResponse(Book entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.translator = entity.getTranslator();
        this.publisher = entity.getPublisher();
        this.publicationDate = entity.getPublicationDate();
        this.description = entity.getDescription();
        this.contents = entity.getContents();
        this.coverImageUrl = entity.getCoverImageUrl();
        this.isbn = entity.getIsbn();
        this.createdDate = entity.getCreatedDate();
        this.updatedDate = entity.getUpdatedDate();
    }
}

