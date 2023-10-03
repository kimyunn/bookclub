package com.example.bookclub.book.service;

import com.example.bookclub.book.dto.request.BookRegisterRequest;
import com.example.bookclub.book.dto.request.BookUpdateRequest;
import com.example.bookclub.book.dto.response.BookResponse;
import com.example.bookclub.book.dto.response.BookMultipleResponse;
import com.example.bookclub.book.entity.Book;
import com.example.bookclub.book.exception.BookDuplicationException;
import com.example.bookclub.book.exception.BookNotFoundException;
import com.example.bookclub.book.repository.BookRepository;
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
    public Long registerBook(BookRegisterRequest request) {
        validateDuplicateIsbn(request.getIsbn());
        return bookRepository.save(request.toEntity()).getId();
    }

    public void validateDuplicateIsbn(Long isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new BookDuplicationException(String.format("입력한 isbn 번호가 존재합니다 isbn: %d", isbn));
        }
    }

    public BookResponse getBook(Long bookId) {
        Book book = findByIdOrThrow(bookId);
        return new BookResponse(book);
    }

    public void deleteBook(Long bookId) {
        Book book = findByIdOrThrow(bookId);
        bookRepository.delete(book);
    }


    public Long updateBook(Long bookId, BookUpdateRequest request) {
        Book book = findByIdOrThrow(bookId);
        BeanUtils.copyProperties(request, book, getNullPropertyNames(request));
        bookRepository.save(book);
        return bookId;
    }

    public Book findByIdOrThrow(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(String.format("도서가 존재하지 않습니다. id: %d", bookId)));
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

    public List<BookMultipleResponse> getNewBooks() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        return bookRepository.findByNewBooks(startDate, endDate).stream()
                .map(BookMultipleResponse::new)
                .collect(Collectors.toList());
    }

    public List<BookMultipleResponse> getSearchBooks(String keyword) {
        return bookRepository.findByKeyword(keyword).stream()
                .map(BookMultipleResponse::new)
                .collect(Collectors.toList());

    }
}

