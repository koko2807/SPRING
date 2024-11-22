package com.aloha.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aloha.community.dto.Board;
import com.aloha.community.dto.Files;
import com.aloha.community.dto.Option;
import com.aloha.community.dto.Page;
import com.aloha.community.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service    // 서비스 역할의 스프링 빈
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileService fileService;
    /**
     * 게시글 목록 조회
     */
    @Override
    public List<Board> list() throws Exception {
        List<Board> List = boardMapper.list(new Option(), new Page());
        return List;
    }

    /**
     * 게시글 조회
     * - no 매개변수로 게시글 번호를 전달받아서
     *   데이터베이스에 조회 요청
     */
    @Override
    public Board select(String id) throws Exception {
        Board board = boardMapper.select(id);
        return board;        
    }

    
    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        
        List<MultipartFile> fileList = board.getFileList();
        
        if (fileList != null )
        for (MultipartFile file : fileList) {
            // 빈 파일이 넘어온 경우
            if (file != null && file.isEmpty())
                continue;

            Files uploadFile = new Files();
            uploadFile.setFile(file);
            uploadFile.setParentTable("board");
            uploadFile.setParentNo(board.getNo());
            uploadFile.setType("main");
            fileService.upload(uploadFile);
        }
        
        return result;
    }
    
    @Override
    public int update(Board board) throws Exception {
        // 게시글 정보 수정
        int result = boardMapper.update(board);
        
        // 삭제할 파일 처리
        List<String> deleteFiles = board.getDeleteFiles();
        if(deleteFiles != null && !deleteFiles.isEmpty() )
        for (String fileId : deleteFiles) {
            log.info("fileId : " + fileId);
            fileService.delete(fileId); // 파일 삭제 요청
        }
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        
        Board board = boardMapper.select(id);
        // 게시글 삭제
        int result = boardMapper.delete(id);

        // 첨부파일 종속 삭제
        Files deleteFile = new Files();
        deleteFile.setParentTable("board");;
        deleteFile.setParentNo(board.getNo());
        int fileResult = fileService.deleteByParent(deleteFile);
        log.info("fileResult : " + fileResult);
        return result;
    }

    // 검색
    @Override
    public List<Board> list(String keyword) throws Exception {
        // List<Board> boardList = boardMapper.list(keyword);
        Option option = new Option();
        option.setKeyword(keyword);
        List<Board> boardList = boardMapper.list(new Option(), new Page());
        return boardList;
    }

    @Override
    public List<Board> list(Option option) throws Exception {
        List<Board> boardList = boardMapper.list(new Option(), new Page());
        return boardList;
    }

    @Override
    public List<Board> list(Option option, int rows) throws Exception {
        List<Board> boardList = boardMapper.list(option, new Page());
        return boardList;
    }

    @Override
    public List<Board> list(Option option, Page page) throws Exception {
        // 데이터 개수
        int total = count(option);
        page.setTotal(total);
        
        List<Board> boardList = boardMapper.list(option, page);
        

        return boardList;
    }

    @Override
    public int count(Option option) throws Exception {
        return boardMapper.count(option);
    }
    
}