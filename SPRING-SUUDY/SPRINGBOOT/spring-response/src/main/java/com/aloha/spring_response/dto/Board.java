package com.aloha.spring_response.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
  
  private int no;
  private String title;
  private String writer;
  private String content;
  private Date createdAt;
  private Date updatedAt;

  public Board(int no, String title, String writer, String content) {
    this.no = no;
    this.title = title;
    this.writer = writer;
    this.content = content;
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }

  
}
