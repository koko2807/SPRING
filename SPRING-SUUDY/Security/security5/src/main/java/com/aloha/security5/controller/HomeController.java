package com.aloha.security5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class HomeController {
  

  /**
   * 메인 화면
   * @param param
   * @return
   */
  @GetMapping({"", "/"})
  public String home() {

      return "index";
  }
  
  /**
   * 로그인 화면
   * @return
   */
  @GetMapping("/login")
  public String login() {
      return "login";
  }
  

}
