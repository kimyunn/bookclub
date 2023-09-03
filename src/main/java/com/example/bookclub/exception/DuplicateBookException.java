package com.example.bookclub.exception;

public class DuplicateBookException extends RuntimeException{
    public DuplicateBookException() {
        super();
    }

    public DuplicateBookException(String message) {
        super(message);
    }

}
