package com.example.bookclub.book.controller;

import com.example.bookclub.book.dto.request.BookRegisterRequest;
import com.example.bookclub.book.dto.request.BookUpdateRequest;
import com.example.bookclub.book.dto.response.BookResponse;
import com.example.bookclub.book.dto.response.BookMultipleResponse;
import com.example.bookclub.book.service.BookService;
import com.example.bookclub.common.exception.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Tag(name = "book", description = "도서 관련 API")
@Slf4j
@Validated
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @Operation(summary = "도서 등록", description = "새로운 도서 정보를 입력하면, isbn 중복 확인 후 존재하지않는 도서일 경우 등록합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "409", description = "isbn번호가 이미 존재하는 경우", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Long> registerBook(@RequestBody @Valid BookRegisterRequest request) {
        Long id = bookService.registerBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @Operation(summary = "도서 단건 조회", description = "도서 번호(bookId)에 해당하는 도서의 상세 정보를 조회합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "405", description = "해당 도서를 찾을 수 없는 경우", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBook(@Parameter(description = "도서 번호", example = "1") @PathVariable Long bookId) {
        BookResponse responseDto = bookService.getBook(bookId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }


    @Operation(summary = "도서 삭제", description = "도서 번호(bookId)에 해당하는 1개의 도서 정보를 삭제합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "405", description = "해당 도서를 찾을 수 없는 경우", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@Parameter(description = "도서 번호", example = "1") @PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }

    @Operation(summary = "도서 정보 수정", description = "도서 번호(bookId)에 해당하는 도서의 전체 또는 일부 정보를 수정합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "405", description = "해당 도서를 찾을 수 없는 경우", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @PatchMapping("/{bookId}")
    public ResponseEntity<Long> updateBook(@PathVariable Long bookId, @RequestBody BookUpdateRequest request) {
        Long id = bookService.updateBook(bookId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

    @Operation(summary = "신간 도서 조회", description = "현재 날짜를 기준으로 30일 이내 출간된 도서를 조회합니다.")
    @GetMapping("/new")
    public ResponseEntity<Result> getNewBooks() {
        List<BookMultipleResponse> newBooks = bookService.getNewBooks();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Result(newBooks.size(), newBooks));
    }

    @Operation(summary = "도서 검색", description = "검색어가 포함된 도서를 조회합니다.")
    @GetMapping("/search")
    public ResponseEntity<Result> getSearchBooks(@Parameter(description = "검색어", example = "조지 오웰") @RequestParam @NotBlank (message = "검색어를 입력해주세요.") String keyword) {
        List<BookMultipleResponse> searchBooks = bookService.getSearchBooks(keyword);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Result(searchBooks.size(), searchBooks));
    }

    @Getter
    static class Result<T> {
        private int count;
        private T data;

        public Result(int count, T data) {
            this.count = count;
            this.data = data;
        }
    }
}

