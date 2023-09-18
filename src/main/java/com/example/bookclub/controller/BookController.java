package com.example.bookclub.controller;

import com.example.bookclub.dto.request.BookRequestDto;
import com.example.bookclub.dto.response.BookResponseDto;
import com.example.bookclub.dto.response.BookMultipleResponseDto;
import com.example.bookclub.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotBlank;

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
        Long id = bookService.updateBook(bookId, requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

    @GetMapping("/new")
    public ResponseEntity<Page<BookMultipleResponseDto>> getNewBooks(Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getNewBooks(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookMultipleResponseDto>> getSearchBooks(@RequestParam @NotBlank (message = "검색어를 입력해주세요.") String keyword, Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getSearchBooks(keyword, pageable));
    }
}

