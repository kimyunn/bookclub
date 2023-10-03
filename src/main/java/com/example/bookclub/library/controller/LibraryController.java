package com.example.bookclub.library.controller;

import com.example.bookclub.library.dto.request.LibraryRequest;
import com.example.bookclub.library.dto.response.LibraryResponse;
import com.example.bookclub.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "library", description = "내서재 관련 API")
@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Operation(summary = "도서 추가", description = "회원이 선택한 도서를 내서재에 추가합니다.")
    @PostMapping
    public ResponseEntity<Long> registerBook(@RequestBody LibraryRequest request) {
        Long id = libraryService.registerBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @Operation(summary = "도서 단건 삭제", description = "회원이 선택한 도서 1권을 삭제합니다.")
    @DeleteMapping("/{libraryId}")
    public ResponseEntity deleteBook(@Parameter(description = "내서재 도서 번호", example = "1") @PathVariable Long libraryId) {
        libraryService.deleteBook(libraryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "내서재 도서 목록 조회", description = "내서재 추가된 모든 도서를 조회합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<Page<LibraryResponse>> getLibraryBooks(@Parameter(description = "회원번호", example = "1") @PathVariable Long userId, Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(libraryService.getLibraryBooks(userId, pageable));
    }
}


