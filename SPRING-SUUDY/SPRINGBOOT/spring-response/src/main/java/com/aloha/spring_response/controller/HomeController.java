package com.aloha.spring_response.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * @Controller
 * spring mvc 의 HTTP 요청에 응답하는 컨트롤러 클래스로 지정하는 어노테이션
 */
@Slf4j      // 로그를 사용하기 위한 어노테이션
@Controller
public class HomeController {
  
  /**
   * void
   * - URL 과 동일한 뷰 페이지를 응답한다.
   * - /login -> login.html
   */
  @GetMapping("/login")
  public void login() {
    log.info("[GET] - /login - 로그인 페이지");
  }

  /**
   * String
   * - 문자열로 응답할 뷰 페이지를 지정한다.
   * - localhost:8080/, localhost:8080 -> index.html
   * @return
   */
  @GetMapping({"/", ""})        // {"/A", "/B"} - 여러 경로 매핑
  public String home() {
      log.info("[GET] - / - 메인 페이지");
      return "index";           // index.html 화면 응답
  }
  
  /**
   * ♥ @ResponseBody
   * String
   * - 문자열 응답 메시지를 지정한다.
   * @return
   */
  @ResponseBody
  @GetMapping("hello")
  public String hello() {
      log.info("[GET] - /hello - 뷰가 아닌 문자열 본문을 응답");
      return "Hello Spring Boot~!";
  }
  



}
