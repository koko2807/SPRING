package com.aloha.thymeleaf.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.aloha.thymeleaf.domain.Person;
import com.aloha.thymeleaf.domain.UserAuth;
import com.aloha.thymeleaf.domain.Users;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
public class HomeController {

  // ★ 이렇게 하면 넘어온 경로 변수로 html 뷰매핑을 바로 할 수 있다.
  @GetMapping({"/", "/{page}"})
  public String main(@PathVariable(value = "page", required = false) String page,
                      Model model,
                      HttpSession session,
                      Person person,
                      Users loginUser
                      ) { // 매개변수에 객체를 지정하면, 기본생성하여 가져온다.
  // Person
  person.setName("ALOHA");
  person.setAge(25);
  model.addAttribute("person", person);

  // 컬렉션
  List<String> items = List.of("item1", "item2", "item3", "item4", "item5");
  model.addAttribute("items", items);

  // Users - UserAuth 리스트
  List<Users> userList = List.of( Users.builder().username("user1").name("사용자1").build(),
                                  Users.builder().username("user2").name("사용자2").build(),
                                  Users.builder().username("user3").name("사용자3").build(),
                                  Users.builder().username("user4").name("사용자4").build(),
                                  Users.builder().username("user5").name("사용자5").build()
                                );

  UserAuth roleUser = UserAuth.builder().auth("ROLE_USER").build();
  UserAuth roleAdmin = UserAuth.builder().auth("ROLE_ADMIN").build();
  List<UserAuth> authList = List.of( roleUser, roleAdmin );
  for (Users user : userList) {
      user.setAuthList(authList);
  }
  model.addAttribute("userList", userList);


  // 로그인 유저
  loginUser.setNo(1L);
  loginUser.setId( UUID.randomUUID().toString() );
  loginUser.setUsername("ALOHA");
  loginUser.setPassword("123456");
  loginUser.setName("이동현");
  loginUser.setGender("성별");      // 남자 여자
  loginUser.setType("사용자");        // 사용자 관리자
  loginUser.setCreatedAt(new Date());
  loginUser.setUpdatedAt(new Date());
  loginUser.setAuthList(authList);

  session.setAttribute("loginUser", loginUser);
  model.addAttribute("loginUser", loginUser);

  return page == null ? "index" : page;
}


}
