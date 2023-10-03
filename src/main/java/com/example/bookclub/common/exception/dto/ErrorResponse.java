package com.example.bookclub.common.exception.dto;

import com.example.bookclub.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private String code;
    private List<FieldError> errors;

    private ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.code = code.getErrorCode();
        this.errors = new ArrayList<>();
    }

    @Builder
    public ErrorResponse(String message, String code, List<FieldError> errors) {
        this.message = message;
        this.code = code;
        this.errors = errors;
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .code(errorCode.getErrorCode())
                .errors(FieldError.create(bindingResult))
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, Iterator<ConstraintViolation<?>> violationIterator) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .code(errorCode.getErrorCode())
                .errors(FieldError.create(violationIterator))
                .build();
    }



    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        @Builder
        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        private static List<FieldError> create(BindingResult bindingResult) {
            List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }

        private static List<FieldError> create(Iterator<ConstraintViolation<?>> violationIterator) {
            List<FieldError> fieldErrors = new ArrayList<>();
            while (violationIterator.hasNext()) {
                final ConstraintViolation<?> constraintViolation = violationIterator.next();
                log.info("constraintViolation={}", constraintViolation.toString());
                FieldError fieldError = FieldError.builder()
                        .field(constraintViolation.getPropertyPath().toString())
                        .value(constraintViolation.getInvalidValue().toString())
                        .reason(constraintViolation.getMessage())
                        .build();
                fieldErrors.add(fieldError);
            }
            return fieldErrors;
        }
    }
}

