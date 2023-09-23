package com.example.bookclub.book.service;

import com.example.bookclub.book.repository.BookRepository;
import com.example.bookclub.common.error.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("존재하는 isbn번호로 등록 시도할 경우 BusinessException 예외 발생한다.")
    public void duplicateIsbn() {
        //given
        Long isbn = 9788973814725L;

        //when, then
        assertThatThrownBy(() -> bookService.validateDuplicateIsbn(isbn))
                .isInstanceOf(BusinessException.class)
                .hasMessage("이미 등록된 isbn번호입니다.");
    }

    @Test
    @DisplayName("존재하지 않는 번호를 조회하는 경우 예외가 발생한다.")
    public void findByIdOrThrow() {
        //given
        Long bookId = 39L;

        //when, then
        assertThatThrownBy(() -> bookService.findByIdOrThrow(bookId))
                .isInstanceOf(BusinessException.class)
                .hasMessage("해당 도서를 찾을 수 없습니다.");
    }

}
