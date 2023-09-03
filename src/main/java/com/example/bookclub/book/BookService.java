package com.example.bookclub.book;

import com.example.bookclub.book.BookRequestDto;
import com.example.bookclub.book.BookResponseDto;
import com.example.bookclub.book.BookSearchResultDto;
import com.example.bookclub.book.Book;
import com.example.bookclub.book.BookRepository;
import com.example.bookclub.exception.DuplicateBookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 등록 로직
    public Long registerBook(BookRequestDto requestDto) {
        if (bookRepository.findByIsbn(requestDto.getIsbn()) != null) {
            throw new DuplicateBookException("이미 존재하는 isbn 번호입니다.");
        }

        return bookRepository.save(requestDto.toEntity()).getId();
    }


    // 단건 조회 로직
    public BookResponseDto getBook(Long bookId) {
        Book book = findByIdOrThrow(bookId);

        return new BookResponseDto(book);
    }

    // 삭제 로직
    public void deleteBook(Long bookId) {
        Book book = findByIdOrThrow(bookId);

        bookRepository.delete(book);
    }

    // 수정 로직
    public Long updateBook(Long bookId, BookRequestDto requestDto) {
        Book book = findByIdOrThrow(bookId);
        book.update(requestDto);
        bookRepository.save(book);

        return bookId;
    }

    // 도서 존재 유무 확인 처리
    private Book findByIdOrThrow(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));

        return book;
    }

    // 신간 도서 조회 로직
    public List<BookSearchResultDto> searchNewBooks() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        List<Book> findBooks = bookRepository.findByNewBooks(startDate, endDate);
        List<BookSearchResultDto> dtos = entityToDtos(findBooks);

        return dtos;
    }

    // 도서 검색 조회
    public List<BookSearchResultDto> searchBooks(String keyword) {
        List<Book> findBooks = bookRepository.findByKeyword(keyword);
        List<BookSearchResultDto> dtos = entityToDtos(findBooks);

        return dtos;
    }

    //entity -> dto
    private static List<BookSearchResultDto> entityToDtos(List<Book> findBooks) {
        List<BookSearchResultDto> dtos = new ArrayList<>();
        for (Book book : findBooks) {
            BookSearchResultDto dto = BookSearchResultDto.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .translator(book.getTranslator())
                    .publisher(book.getPublisher())
                    .coverImageUrl(book.getCoverImageUrl())
                    .build();

            dtos.add(dto);
        }

        return dtos;
    }
}
