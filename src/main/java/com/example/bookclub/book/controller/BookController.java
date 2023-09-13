package com.example.bookclub.book.controller;

import com.example.bookclub.book.dto.request.BookRequestDto;
import com.example.bookclub.book.dto.response.BookResponseDto;
import com.example.bookclub.book.dto.response.BookMultipleResponseDto;
import com.example.bookclub.book.service.BookService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public ResponseEntity<Long> registerBook(@RequestBody BookRequestDto requestDto) {
        Long id = bookService.registerBook(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long bookId) {
        BookResponseDto responseDto = bookService.getBook(bookId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }


    @PatchMapping("/{bookId}")
    public ResponseEntity<Long> updateBook(@PathVariable Long bookId, @RequestBody BookRequestDto requestDto) {
        log.info("requestDto={}", requestDto.toString());
        Long id = bookService.updateBook(bookId, requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

    @GetMapping("/new")
    public ResponseEntity<Result> getNewBooks() {
        List<BookMultipleResponseDto> newBooks = bookService.getNewBooks();
        log.info("newBookstitle={}", newBooks.get(0).getTitle());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Result(newBooks.size(), newBooks));
    }

    @GetMapping("/search")
    public ResponseEntity<Result> getSearchBooks(@RequestParam @NotBlank (message = "검색어를 입력해주세요.") String keyword) {
        List<BookMultipleResponseDto> searchBooks = bookService.getSearchBooks(keyword);
        log.info("searchBooks={}", searchBooks.toString());

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

