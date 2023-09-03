package com.example.bookclub.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private String status;
    private String message;
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL) // count 필드가 null인 경우 직렬화에서 제외
    private Integer count;

    public ApiResponse(String status,String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String status, T data, Integer count) {
        this.status = status;
        this.data = data;
        this.count = count;
    }

    // 응답 데이터가 하나일 경우
    public static <T> ApiResponse<T> createSuccess(String message, T data) {
        return new ApiResponse<>(SUCCESS_STATUS, message, data);
    }

    // 응답 데이터가 여러개일 경우
    public static <T> ApiResponse<T> createSuccess(T data, Integer count) {
        return new ApiResponse<>(SUCCESS_STATUS, data, count);
    }

    // 요청 에러
    public static ApiResponse<?> createError(String message) {
        return new ApiResponse<>(ERROR_STATUS, message, (Object) null);
    }


}
