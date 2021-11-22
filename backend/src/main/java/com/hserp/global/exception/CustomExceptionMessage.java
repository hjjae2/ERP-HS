package com.hserp.global.exception;

public class CustomExceptionMessage {

    public static final String BAD_REQUEST_MESSAGE = "적절하지 않은 요청입니다.";
    public static final String DATA_NOT_FOUND_MESSAGE = "해당 데이터를 확인할 수 없습니다.";
    public static final String INTERNAL_EXCEPTION_MESSAGE = "서버 상에 오류가 발생하였습니다.";

    public static String BAD_REQUEST_MESSAGE(String causeMessage) {
        return (causeMessage != null)
                ? String.format("%s (%s)", BAD_REQUEST_MESSAGE, causeMessage)
                : BAD_REQUEST_MESSAGE;
    }

    public static String DATA_NOT_FOUND_MESSAGE(String causeMessage) {
        return (causeMessage != null)
                ? String.format("%s (%s)", DATA_NOT_FOUND_MESSAGE, causeMessage)
                : DATA_NOT_FOUND_MESSAGE;
    }

    public static String INTERNAL_EXCEPTION_MESSAGE(String causeMessage) {
        return (causeMessage != null)
                ? String.format("%s (%s)", INTERNAL_EXCEPTION_MESSAGE, causeMessage)
                : INTERNAL_EXCEPTION_MESSAGE;
    }
}
