package com.example.bookclub.book;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Entity
public class Book extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 20)
    private String author;

    @Column(length = 20)
    private String translator;

    @Column(nullable = false, length = 20)
    private String publisher;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(nullable = false, length = 300)
    private String coverImageUrl;

    @Column(nullable = false, unique = true)
    private Long isbn;

    public void setId(Long id) {
        this.id = id;
    }

    @Builder
    public Book(Long id, String title, String author,
                String translator, String publisher, LocalDate publicationDate,
                String description, String contents, String coverImageUrl, Long isbn) {
        this.id = id;
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

    public void update(BookRequestDto requestDto) {
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getAuthor() != null) {
            this.author = requestDto.getAuthor();
        }
        if (requestDto.getTranslator() != null) {
            this.translator = requestDto.getTranslator();
        }
        if (requestDto.getPublisher() != null) {
            this.publisher = requestDto.getPublisher();
        }
        if (requestDto.getPublicationDate() != null) {
            this.publicationDate = requestDto.getPublicationDate();
        }
        if (requestDto.getDescription() != null) {
            this.description = requestDto.getDescription();
        }
        if (requestDto.getContents() != null) {
            this.contents = requestDto.getContents();
        }
        if (requestDto.getCoverImageUrl() != null) {
            this.coverImageUrl = requestDto.getCoverImageUrl();
        }
    }
}

