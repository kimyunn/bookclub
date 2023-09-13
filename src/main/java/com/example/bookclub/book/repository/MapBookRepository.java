package com.example.bookclub.book.repository;

import com.example.bookclub.book.entity.Book;

import java.time.LocalDate;
import java.util.*;

public class MapBookRepository implements BookRepository{
    private static final Map<Long, Book> db = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(++sequence);
        }
        db.put(book.getId(), book);
        return db.get(book.getId());
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return Optional.ofNullable(db.get(bookId));
    }

    @Override
    public void delete(Book book) {
        db.remove(book.getId());
    }

    @Override
    public List<Book> findByNewBooks(LocalDate startDate, LocalDate endDate) {
        List<Book> findBooks = new ArrayList<>();

        for (Long key : db.keySet()) {
            LocalDate publicationDate = db.get(key).getPublicationDate();

            if (publicationDate.isAfter(startDate) && publicationDate.isBefore(endDate)) {
                findBooks.add(db.get(key));
            }
        }

        return findBooks;
    }

    @Override
    public List<Book> findByKeyword(String keyword) {
        List<Book> findBooks = new ArrayList<>();

        for (Long key : db.keySet()) {
            String title = db.get(key).getTitle();
            String author = db.get(key).getAuthor();
            String translator = db.get(key).getTranslator();

            if (title.contains(keyword) || author.contains(keyword) || translator.contains(keyword)) {
                findBooks.add(db.get(key));
            }
        }

        return findBooks;
    }

    @Override
    public Boolean existsByIsbn(Long isbn) {
        for (Long key : db.keySet()) {
            Long existingBookIsbn = db.get(key).getIsbn();

            if (existingBookIsbn.equals(isbn)) {
                return true;
            }
        }
        return false;
    }


}