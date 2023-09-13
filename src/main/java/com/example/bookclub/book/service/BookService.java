package com.example.bookclub.book.service;

import com.example.bookclub.book.dto.request.BookRequestDto;
import com.example.bookclub.book.dto.response.BookResponseDto;
import com.example.bookclub.book.dto.response.BookMultipleResponseDto;
import com.example.bookclub.book.entity.Book;
import com.example.bookclub.book.repository.BookRepository;
import com.example.bookclub.common.error.exception.BusinessException;
import com.example.bookclub.common.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Long registerBook(BookRequestDto requestDto) {
        validateDuplicateIsbn(requestDto.getIsbn());

        return bookRepository.save(requestDto.toEntity()).getId();
    }

    public void validateDuplicateIsbn(Long isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new BusinessException(ErrorCode.DUPLICATE_BOOK_ISBN);
        }
    }

    public BookResponseDto getBook(Long bookId) {
        Book book = findByIdOrThrow(bookId);

        return new BookResponseDto(book);
    }

    public void deleteBook(Long bookId) {
        Book book = findByIdOrThrow(bookId);
        bookRepository.delete(book);
    }


    public Long updateBook(Long bookId, BookRequestDto requestDto) {
        log.info("dtoBooktitle={}", requestDto.getTitle());
        Book book = findByIdOrThrow(bookId);
        BeanUtils.copyProperties(requestDto, book, getNullPropertyNames(requestDto));
        bookRepository.save(book);
        log.info("entityBooktitle={}" , book.getTitle());

        return bookId;
    }

    private Book findByIdOrThrow(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BusinessException(ErrorCode.BOOK_NOT_FOUND));

        return book;
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public List<BookMultipleResponseDto> getNewBooks() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        log.info("endDate={}", endDate);
        log.info("startDate={]", startDate);

        return bookRepository.findByNewBooks(startDate, endDate).stream()
                .map(BookMultipleResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<BookMultipleResponseDto> getSearchBooks(String keyword) {
        return bookRepository.findByKeyword(keyword).stream()
                .map(BookMultipleResponseDto::new)
                .collect(Collectors.toList());

    }
}

