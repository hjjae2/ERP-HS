package com.hserp.global.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiLogDto {

    String uri;
    String method;
    String handler;
    String request;
    String response;
    String processingTime;
}
