package com.aloha.spring_response.controller;

import java.util.ArrayList;
import java.util.List;

// 게시글 목록/조회
// [GET]  - /board/list    - /board/list.html
// [GET]  - /board/read    - /board/read.html

// 게시글 등록
// [GET]  - /board/insert  - /board/insert.html
// [POST] - /board/insert  - redirect:/board/list

// 게시글 수정
// [GET]  - /board/update  - /board/update.html
// [POST] - /board/update  - redirect:/board/list

// 게시글 삭제
// [POST] - /board/delete  - redirect:/board/list

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.spring_response.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

  /**
   * 게시글 목록
   * model : boardList
   * @param model
   * @return
   */
  @GetMapping("/list")
  public String list(Model model) {
    log.info("[GET] - /board/list");

    // 데이터 요청
    // 생성자 세팅 부터 이어서...
    List<Board> boardList = new ArrayList<>();
        boardList.add(new Board(1, "제목1", "작성자1", "내용1"));
        boardList.add(new Board(2, "제목2", "작성자2", "내용2"));
        boardList.add(new Board(3, "제목3", "작성자3", "내용3"));

    // 모델 등록
      model.addAttribute("boardList", boardList);

    // 뷰 페이지 지정
    return "board/list";
}

/**
 * 게시글 조회
 * /board/read?no=★
 * mode : board
 * @param model
 * @param no
 * @return
 */
  @GetMapping("/read")
  public String read(Model model, @RequestParam("no") int no) {
    log.info("[GET] - /board/read");
    log.info("no : " + no);

    // 데이터 조회 요청
    Board board = Board.builder()
                        .no(no)
                        .title("제목")
                        .writer("작성자")
                        .content("내용")
                        .build();
    // 모델 등록
    model.addAttribute("board", board);

    // 뷰 페이지 지정
    return "board/read";
}

/**
 * 게시글 등록
 * [GET]
 * /board/insert
 * @return
 */
  @GetMapping("/insert")
  public String insert() {
    // 뷰 페이지 지정
    return "board/insert";
}

/**
 * 게시글 등록 처리
 * [POST]
 * /board/insert
 * <form> -> Board
 * @param board
 * @return
 */
  @PostMapping("/insert")
  public String insertPost(Board board) {
    log.info("[POST] - /board/insert");
    log.info("board : " + board);
    
    // 데이터 등록 요청
    int result = new Random().nextInt(2);     // 0 또는 1
    if( result == 0 )
        return "redirect:/board/insert";    // redirect -> 글등록 페이지

    return "redirect:/board/list";          // redirect -> 글목록 페이지
}

/**
 * 게시글 수정
 * [GET]
 * /board/update
 * model : board
 * @param model
 * @param no
 * @return
 */
  @GetMapping("/update")
  public String update(Model model, @RequestParam("no") int no) {
    log.info("no : " + no);
    // 데이터 조회 요청
    Board board = Board.builder()
                        .no(no)
                        .title("제목")
                        .writer("작성자")
                        .content("내용")
                        .build();
    // 모델 등록
    model.addAttribute("board", board);

    // 뷰 페이지 지정
    return "board/update";
}

/**
 * 게시글 수정 처리
 * [POST]
 * /board/update
 * <form> -> board
 * @param board
 * @return
 */
  @PostMapping("/update")
  public String updatePost(Board board) {
    log.info("board : " + board);

    // 데이터 수정 요청
    int result = new Random().nextInt(2);
    if( result == 0 )
    // redirect -> /board/update?no=★
    return "redirect/board/update?no=" + board.getNo();

    //redirect -> /board/list
    return "redirect:/board/list";
}

  /**
   * 게시글 삭제 처리
   * [POST]
   * /board/delete
   * <form> -> no
   * @param no
   * @return
   */
  @PostMapping("/delete")
  public String deletePost(@RequestParam("no")int no) {
    log.info("no : " + no);
    
    // 데이터 삭제 요청
    int result = new Random().nextInt(2);
    if( result == 0 )
    // redirect -> /board/delete?no=★
    return "redirect/board/update?no=" + no;

    return "redirect:/board/list";
}

/**
 * 게시글 수정 비동기 처리
 * [PUT]
 * /board
 * JSON -> Board (★@RequestBody)
 * @param board
 * @return
 */
@ResponseBody
@PutMapping("")
public ResponseEntity<String> putUpdate(@RequestBody Board board) {
    log.info("[PUT] - /board");
    log.info("board : " + board);

    int result = new Random().nextInt(2);
    if( result == 0 )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("FAIL");

    return ResponseEntity.ok("SUCCESS");
}

/**
 * 게시글 삭제 비동기 처리
 * [DELETE]
 * /board?no=★
 * JSON -> no
 * @param no
 * @return
 */
@ResponseBody
@DeleteMapping
public ResponseEntity<String> delete(@RequestParam("no") int no) {
  log.info("[DELETE] - /board");
  log.info("no : " + no);

  int result = new Random().nextInt(2);
  if( result == 0 )
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("FAIL");

  return ResponseEntity.ok("SUCCESS");
}


}
