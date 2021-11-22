package com.hserp.global.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;

    @Builder
    public ApiResponse(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse success(Object data) {
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(data)
                .build();
    }

    public static ApiResponse success(Object data, String message) {
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse error(HttpStatus e) {
        return ApiResponse.builder()
                .code(e.value())
                .message(e.getReasonPhrase())
                .data(new ArrayList<>())
                .build();
    }

    public static ApiResponse error(HttpStatus e, String data) {
        return ApiResponse.builder()
                .code(e.value())
                .message(e.getReasonPhrase())
                .data(data)
                .build();
    }
}
