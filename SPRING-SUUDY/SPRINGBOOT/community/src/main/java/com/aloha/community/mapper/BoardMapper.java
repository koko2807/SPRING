package com.aloha.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.community.dto.Board;
import com.aloha.community.dto.Option;
import com.aloha.community.dto.Page;

@Mapper
public interface BoardMapper {

    // 게시글 목록
    // public List<Board> list() throws Exception;
    // public List<Board> list(String keyword) throws Exception;
    // public List<Board> list(@Param("option") Option option) throws Exception;
    // public List<Board> list( @Param("option") Option option 
    //                         ,@Param("rows") int rows) throws Exception;
    public List<Board> list( @Param("option") Option option 
                            ,@Param("page") Page page) throws Exception;
    // 게시글 조회
    public Board select(@Param("id") String id) throws Exception;
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 게시글 삭제
    public int delete(String id) throws Exception;

    // 데이터 개수
    public int count(@Param("option") Option option) throws Exception;

}