package com.example.bookclub.library.dto.response;

import com.example.bookclub.library.entity.Library;
import lombok.Getter;

@Getter
public class LibraryResponse {
    private Long id;
    private String title;
    private String author;
    private String translator;
    private String publisher;
    private String coverImageUrl;

    public LibraryResponse(Library library) {
        this.id = library.getId();
        this.title = library.getBookId().getTitle();
        this.author = library.getBookId().getAuthor();
        this.translator = library.getBookId().getTranslator();
        this.publisher = library.getBookId().getPublisher();
        this.coverImageUrl = library.getBookId().getCoverImageUrl();
    }
}
