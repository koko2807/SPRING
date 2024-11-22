package com.aloha.spring_http.dto;

import java.util.Map;

import lombok.Data;

@Data
public class HttpResponse {
    private Map<String, String> args;     // 요청 매개변수
    private Map<String, String> headers;  // 응답 헤더
    private String origin;                // 요청 주소
    private String url;                   // 요청 URL
}