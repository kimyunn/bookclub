package com.example.bookclub.book.repository;

import com.example.bookclub.dto.request.BookRequestDto;
import com.example.bookclub.domain.Book;
import com.example.bookclub.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("도서 등록 성공 ")
    public void bookRegisterSuccess() {
        //given
        BookRequestDto requestBook = BookRequestDto.builder()
                .title("노인과바다")
                .author("어니스트 헤밍웨이")
                .translator("김욱동")
                .publisher("민음사")
                .publicationDate(LocalDate.of(2012,01,02))
                .description("먼 바다에서 펼쳐지는 노인의 고독한 사투!")
                .contents("목차")
                .coverImageUrl("https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788937462788.jpg")
                .isbn(9788937462788L)
                .build();

        //when
        Book findBook = bookRepository.save(requestBook.toEntity());

        //then
        assertThat(findBook.getTitle()).isEqualTo(requestBook.getTitle());
        assertThat(findBook.getAuthor()).isEqualTo(requestBook.getAuthor());
    }

    @Test
    @DisplayName("isbn번호로 이미 존재하는지를 확인할 수 있다.")
    public void existsByIsbn() {
        //given
        Long isbn = 9788973814725L;

        //when, then
        assertThat(bookRepository.existsByIsbn(isbn)).isTrue();
    }

    @Test
    @DisplayName("도서 번호로 도서 상세 정보를 조회할 수 있다.")
    public void getBook() {
        //given
        Long bookId = 42L;
        String title = "멋진 신세계";
        String author = "올더스 헉슬리";

        //when
        Book findBook = bookRepository.findById(bookId).get();

        //then
        assertThat(findBook.getTitle()).isEqualTo(title);
        assertThat(findBook.getAuthor()).isEqualTo(author);
    }

    @Test
    @DisplayName("도서 번호로 해당 도서를 삭제할 수 있다.")
    public void delete() {
        // given
        Long bookId = 38L;
        Book book = bookRepository.findById(bookId).get();
        bookRepository.delete(book);

        // when
        Optional<Book> findBook = bookRepository.findById(bookId);

        // then
        assertThat(findBook).isEmpty();

    }



}
