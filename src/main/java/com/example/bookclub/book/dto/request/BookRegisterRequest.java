package com.example.bookclub.book.dto.request;

import com.example.bookclub.book.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookRegisterRequest {

    @Schema(description = "제목", example = "1984")
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Schema(description = "저자", example = "조지 오웰")
    @NotBlank(message = "저자를 입력해주세요.")
    private String author;

    @Schema(description = "역자", example = "정회성")
    private String translator;

    @Schema(description = "출판사", example = "민음사")
    @NotBlank(message = "출판사를 입력해주세요.")
    private String publisher;

    @Schema(description = "출간일", example = "2007-03-30")
    @NotNull(message = "날짜를 선택해주세요.")
    private LocalDate publicationDate;

    @Schema(description = "책소개", example = "과거를 지배하는 자는 미래를 지배하고, 현재를 지배하는 자는 과거를 지배한다!")
    @NotBlank(message = "책소개를 입력해주세요.")
    private String description;

    @Schema(description = "목차", example = "목차")
    private String contents;

    @Schema(description = "커버이미지", example = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788937460777.jpg")
    @NotBlank(message = "커버이미지 주소를 입력해주세요.")
    private String coverImageUrl;

    @Schema(description = "isbn", example = "9788937460777")
    @NotNull(message = "isbn번호를 입력해주세요.")
    private Long isbn;

    @Builder
    public BookRegisterRequest(String title, String author,
                               String translator, String publisher,
                               LocalDate publicationDate, String description,
                               String contents, String coverImageUrl, Long isbn) {
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

