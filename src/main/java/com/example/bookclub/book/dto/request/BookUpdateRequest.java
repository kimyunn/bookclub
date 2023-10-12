package com.example.bookclub.book.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class BookUpdateRequest {

    @Schema(description = "제목", example = "1984")
    private String title;

    @Schema(description = "저자", example = "조지 오웰")
    private String author;

    @Schema(description = "역자", example = "정회성")
    private String translator;

    @Schema(description = "출판사", example = "민음사")
    private String publisher;

    @Schema(description = "출간일", example = "2007-03-30")
    private LocalDate publicationDate;

    @Schema(description = "책소개", example = "과거를 지배하는 자는 미래를 지배하고, 현재를 지배하는 자는 과거를 지배한다!")
    private String description;

    @Schema(description = "목차", example = "목차")
    private String contents;

    @Schema(description = "커버이미지", example = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788937460777.jpg")
    private String coverImageUrl;

}
