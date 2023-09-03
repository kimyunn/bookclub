package com.example.bookclub.book;

public class DuplicateBookException extends RuntimeException{
    public DuplicateBookException() {
        super();
    }

    public DuplicateBookException(String message) {
        super(message);
    }

}
