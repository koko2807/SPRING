package com.aloha.spring_response.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.spring_response.dto.Board;
import com.aloha.spring_response.dto.Person;
import com.aloha.spring_response.dto.Student;

import lombok.extern.slf4j.Slf4j;

/**
 * 🟢🟡🔴 @RestController
 * : JSON 또는 XML과 같은 데이터를 반환하는 컨트롤러를 지정하는 어노테이션
 * 🔵 RESTful 웹 서비스를 생성하는 데 사용
 * 🔵 @Controller 와 @ResponseBody를 합한 역할을 하는 어노테이션
 * ✔ @Controller       ➡️ View를 반환
 * ✔ @RestController   ➡️ 응답 데이터(메시지[상태코드,응답헤더,응답본문(body)])를 반환
 */
@Slf4j
@RestController
public class ResponseController {
  
  /**
   * void
   * - 어떤 작업을 수행하고 응답이 필요 없는 경우
   * 상태코드 : 200 OK
   * body    : X
   */
  @GetMapping("/void")
  public void voidResponse() {
      log.info("[GET] - /void");
  }
  
  /**
   * String
   * - 반환하는 문자열로, HTTP 응답 본문(body)에 지정한다.
   * 상태코드 : 200 OK
   * body    : "지정한 문자열"
   */
  @GetMapping("/string")
  public String stringResponse() {
      log.info("[GET] - /string");
      return "지정한 문자열";
  }

  /**
   * 객체
   * - 객체를 JSON 형식으로 변환하고, 이를 HTTP 응답 본문(body)에 지정한다.
   * 상태코드 : 200 OK
   * body    : {"no:0, "title":"제목", "writer":"작성자", "content":"내용",...}
   * @return
   */
  @GetMapping("/object")
  public Board objectResponse() {
      Board board = Board.builder()
                          .title("제목")
                          .writer("작성자")
                          .content("내용")
                          .build();
      log.info("board - " + board);
      return board;
  }
  
  /**
   * 컬렉션
   * - 컬렉션을 JSON 배열 형식으로 변환하고, 이를 HTTP 응답 본문에 지정한다.
   * 상태코드 : 200 OK
   * body     : [ {"no:0, "title":"제목", "writer":"작성자", "content":"내용",...}, ... ]
   * @return
   */
  @GetMapping("/collection")
  public List<Board> collectionResponse() {
      log.info("[GET] - /collection");
      List<Board> boardList = new ArrayList<>();
      boardList.add(Board.builder().title("제목1").writer("작성자1").content("내용1").build());
      boardList.add(Board.builder().title("제목2").writer("작성자2").content("내용2").build());
      boardList.add(Board.builder().title("제목3").writer("작성자3").content("내용3").build());
      return boardList;
  }

  /**
   * Map<String, ?>
   * - Map 컬렉션을 JSON 형식으로 변환하여,
   * key 에 대한 value 로 다양한 객체들을 계층구조로 HTTP 응답 
   * @return
   */
  @GetMapping("/map")
  public Map<String, ?> mapResponse() {
        log.info("[GET] - /map");

        Map<String, Object> map = new HashMap<>();
        map.put("board", Board.builder()
                                    .title("제목")
                                    .writer("작성자")
                                    .content("컨텐츠")
                                    .build());

        map.put("person", new Person());
        map.put("student", new Student());
        return map;
  }
  
  /**
   * ResponseEntity<★>
   * : ResponseEntity 객체를 사용하면,
   * "상태코드/헤더/본문"을 지정하여 응답할 수 있다.
   *  <★> 타입 매개변수에 응답할 데이터 타입을 지정할 수 있다.
   * - String, Object, Collection 등 지정 가능
   * - ? 와일드 카드를 지정하면, 특정하지 않고 동적으로 지정 가능
   * @param param
   * @return
   */
  @GetMapping("/responseString")
  public ResponseEntity<String> responseEntityResponse(@RequestParam String param) {
      log.info("[GET] - /responseString");

    // 상태코드 : 200 OK
    // 1) return ResponseEntity.ok("200 OK - 요청 성공");
    // 2) return new ResponseEntity<>(HttpStatus.OK);
    // 3) return new ResponseEntity<>("200 OK - 요청 성공", HttpStatus.OK);

    // 상태코드 : 400
    // 1) return ResponseEntity.badRequest("400 Bad Request - 잘못된 요청");
    // 2) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // 3) return new ResponseEntity<>("400 Bad Request - 잘못된 요청", HttpStatus.BAD_REQUEST);

    // 상태코드 : 500
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        // .body("500 Internal Server Error - 서버 내부 에러");

    return ResponseEntity.ok("200 OK - 요청 성공");
  }
  
  /**
   * 이미지 썸네일
   * ResponseEntity<byte[]>
   * @return
 * @throws IOException 
   */
  @GetMapping("/img")
  public ResponseEntity<byte[]> thumbnailImg() throws IOException {
      log.info("[GET] - /img");

      // 이미지 파일 읽어옴
      // - src/main/resources 경로의 파일을 지정하여 입력
      ClassPathResource imgFile = new ClassPathResource("sample.png");
      // 파일 데이터
      byte[] bytes = imgFile.getContentAsByteArray();

      // 이미지 파일 응답
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_PNG);        // image/png
      return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
  }
  
  /**
   * 이미지 썸네일
   * ResponseEntity<byte[]>
   * @return
    * @throws IOException 
      */
     @GetMapping("/download")
     public ResponseEntity<byte[]> download() throws IOException {
      log.info("[GET] - /download");

      // 이미지 파일 읽어옴
      // - src/main/resources 경로의 파일을 지정하여 입력
      ClassPathResource imgFile = new ClassPathResource("sample.png");
      // 파일 데이터
      byte[] bytes = imgFile.getContentAsByteArray();

      // 이미지 파일 응답
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // application.octet-stream
      headers.setContentDispositionFormData("attachment", "sample.png");
      return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
  }
}
