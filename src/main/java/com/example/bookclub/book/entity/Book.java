package com.example.bookclub.book.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Book extends BaseTimeEntity {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

}

