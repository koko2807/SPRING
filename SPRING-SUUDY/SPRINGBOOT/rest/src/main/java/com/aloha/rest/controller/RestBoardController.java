package com.aloha.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.rest.dto.Board;
import com.aloha.rest.service.BoardService;

import lombok.extern.slf4j.Slf4j;

/**
 * ♥ Restful
 * 게시판 REST 컨트롤러
 * - 게시글 목록      - [GET]        /boards    (param)
 * - 게시글 조회      - [GET]        /boards/10 (param)
 * - 게시글 등록      - [POST]       /boards    (body)
 * - 게시글 수정      - [PUT]        /boards    (body)
 * - 게시글 삭제      - [DELETE]     /boards/10 (param)
 */
@Slf4j
@RestController
@RequestMapping("/boards")
public class RestBoardController {

  @Autowired private BoardService boardService;

  // sp-crud ♥ spring code generator 확장 설치로 자동 완성 키워드 사용
  
  /**
   * 게시글 목록
   * @return
   */
  @GetMapping()
  public ResponseEntity<?> getAll() {
      try {
          List<Board> boardList = boardService.list();
          return new ResponseEntity<>(boardList, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  /**
   * 게시글 조회
   * @param no
   * @return
   */
  @GetMapping("/{no}")
  public ResponseEntity<?> getOne(@PathVariable Integer no) {
      try {
          Board board = boardService.select(no);
          //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
          return new ResponseEntity<>("GetOne Result", HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  /**
   * 게시글 등록
   * @param board
   * @return
   */
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody Board board) {
      try {
          int result = boardService.insert(board);
          if ( result > 0 ) {
          return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED); // 201 CREATED
        }
        else {
         return new ResponseEntity<>("FAIL", HttpStatus.OK);
        }
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  /**
   * 게시글 수정
   * @param board
   * @return
   */
  @PutMapping()
  public ResponseEntity<?> update(@RequestBody Board board) {
      try {
        int result = boardService.update(board);
          if ( result > 0) {
          return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else {
         return new ResponseEntity<>("FAIL", HttpStatus.OK);
      }
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @DeleteMapping("/{no}")
  public ResponseEntity<?> destroy(@PathVariable("no") Integer no) {
      try {
        int result = boardService.delete(no);
        if ( result > 0) {
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
      }
      else {
       return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}