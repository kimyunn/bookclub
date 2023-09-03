package com.example.bookclub.book;


import com.example.bookclub.exception.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 상품 등록
    @PostMapping("/books")
    public ResponseEntity<ApiResponse<Long>> registerBook(@RequestBody BookRequestDto requestDto) {
        Long id = bookService.registerBook(requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.createSuccess("등록되었습니다", id));
    }

    // 도서 조회
    @GetMapping("/books/{bookId}")
    public ResponseEntity<ApiResponse<BookResponseDto>> getBook(@PathVariable Long bookId) {
        BookResponseDto responseDto = bookService.getBook(bookId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.createSuccess(null, responseDto));
    }

    // 도서 삭제
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<ApiResponse<Long>> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.createSuccess("삭제되었습니다", bookId));

    }

    // 도서 수정
    @PatchMapping("/books/{bookId}")
    public ResponseEntity<ApiResponse<Long>> updateBook(@PathVariable Long bookId, @RequestBody BookRequestDto requestDto) {
        Long id = bookService.updateBook(bookId, requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.createSuccess("수정되었습니다", id));
    }

    // 신간 도서 조회
    @GetMapping("/books/new")
    public ResponseEntity<ApiResponse<List<BookSearchResultDto>>> searchNewBooks() {
        List<BookSearchResultDto> newBooks = bookService.searchNewBooks();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.createSuccess(newBooks, newBooks.size()));

    }

    // 도서 검색
    @GetMapping("/books/search")
    public ResponseEntity<ApiResponse<List<BookSearchResultDto>>> searchBooks(@RequestParam String keyword) {
        List<BookSearchResultDto> newBooks = bookService.searchBooks(keyword);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.createSuccess(newBooks, newBooks.size()));


    }
}
