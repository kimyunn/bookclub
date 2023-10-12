package com.example.bookclub.library.service;

import com.example.bookclub.book.entity.Book;
import com.example.bookclub.book.exception.BookNotFoundException;
import com.example.bookclub.book.repository.BookRepository;
import com.example.bookclub.library.dto.request.LibraryRequest;
import com.example.bookclub.library.dto.response.LibraryResponse;
import com.example.bookclub.library.entity.Library;
import com.example.bookclub.library.repository.LibraryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    private LibraryRepository libraryRepository;
    private BookRepository bookRepository;

    public LibraryService(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    // 내서재에 도서 추가하기
    public Long registerBook(LibraryRequest request) {
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new BookNotFoundException(String.format("도서가 존재하지 않습니다. id: %d", request.getBookId())));
        return libraryRepository.save(Library.createLibrary(request.getUserId(), book)).getId();
    }

    // 내서재 도서 삭제
    public void deleteBook(Long libraryId) {
        libraryRepository.deleteById(libraryId);
    }

    // 내서재 도서 목록 조회
    public Page<LibraryResponse> getLibraryBooks(Long userId, Pageable pageable) {
        return libraryRepository.findByUserId(userId, pageable).map(library -> new LibraryResponse(library));
    }


}
