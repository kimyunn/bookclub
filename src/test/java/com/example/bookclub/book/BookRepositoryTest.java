package com.example.bookclub.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void 도서_등록() {
        // Given
        String expectedTitle = "속죄";
        BookRequestDto requestDto = BookRequestDto.builder()
                .title(expectedTitle)
                .author("이언 매큐언")
                .translator("한정아")
                .publisher("문학동네")
                .publicationDate(LocalDate.now())
                .description("세계문화전집 233")
                .contents("책소개")
                .coverImageUrl("이미지")
                .isbn(209348025732L)
                .build();

        bookRepository.save(requestDto.toEntity());
        
        // When
        Book actual = bookRepository.findById(32L).orElseThrow(IllegalArgumentException::new);

        // Then
        assertEquals(expectedTitle, actual.getTitle());

    }

    @Test
    public void isbn_중복_여부_확인_테스트() {
        // Given
        Long isbn = 9788937460050L;

        // When
        Book book = bookRepository.findByIsbn(isbn);

        // Then
        assertNotNull(book);
    }

    @Test
    public void 도서_조회_성공_존재하는_id_입력() {
        // Given
        Long id = 1L;
        String expectedTitle = "동물농장";

        // When
        Book actual = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        // Then
        assertEquals(expectedTitle, actual.getTitle());
    }

    @Test
    public void 도서_조회_실패_존재하지_않는_id_입력() {
        // Given

        // When
        Optional<Book> actual = bookRepository.findById(12L);

        // Then
        assertFalse(actual.isPresent());
    }

    @Test
    public void 도서_수정_성공() {
        // Given
        Long id = 3L;
        String expectedTitle = "속죄";
        BookRequestDto requestDto = BookRequestDto.builder()
                .title(expectedTitle)
                .author("이언 매큐언")
                .build();

        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        book.update(requestDto);
        bookRepository.save(book);

        // When
        Book actual = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        // Then
        assertEquals(expectedTitle, actual.getTitle());
    }

    @Test
    public void 도서_삭제_성공() {
        // Given
        Long id = 5L;
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        bookRepository.delete(book);
        // When

        Book actual = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        // Then
        assertEquals(null, actual);
    }

    @Test
    public void 도서_검색_성공() {
        // Given
        String keyword = "조지";
        int expectedSize = 7;

        // When
        List<Book> findBooks = bookRepository.findByKeyword(keyword);

        // Then
        assertEquals(expectedSize, findBooks.size());
    }

    @Test
    public void 도서_신간_조회_성공() {
        // Given
        int expectedSize = 7;
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);

        // When
        List<Book> findBooks = bookRepository.findByNewBooks(startDate, endDate);

        // Then
        assertEquals(expectedSize, findBooks.size());
    }



}
