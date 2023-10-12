package com.example.bookclub.library.repository;

import com.example.bookclub.book.entity.Book;
import com.example.bookclub.library.entity.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibraryRepository {

    Library save(Library library); // 내서재에 도서 추가하기
    Page<Library> findByUserId(Long userId, Pageable pageable); // 도서 목록 조회
    void deleteById(Long libraryId); // 도서 한권 삭제
}
