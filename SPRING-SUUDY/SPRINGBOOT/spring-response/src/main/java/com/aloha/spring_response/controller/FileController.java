package com.aloha.spring_response.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.spring_response.util.MediaUtil;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {

    /**
     * 이미지 썸네일
     * 요청 경로 : /file/img?filePath=C:/upload/sample.png
     * @param filePath
     * @param response
     * @throws IOException 
     */
    @GetMapping("/img")
    public void showImg( @RequestParam("filePath") String filePath
                         ,HttpServletResponse response) throws IOException {
       log.info("filePath : " + filePath);

       File file = new File(filePath);
       FileInputStream fis = new FileInputStream(file);
       ServletOutputStream sos = response.getOutputStream();
       FileCopyUtils.copy(fis, sos);    // 입력한 파일 출력

       // 확장자로 컨텐츠 타입 지정
       // - 확장자 : .jpg, .png ...
       String ext = filePath.substring(filePath.lastIndexOf(".") + 1); // 확장자
       MediaType mediaType = MediaUtil.getMediaType(ext);
       if( mediaType == null ) return;  // 이미지 타입이 아닐 경우
       log.info("mediaType : " + mediaType);
       response.setContentType( mediaType.toString() ); // image/jpeg
    }
    


    @GetMapping("/download")
    public void fileDownload( @RequestParam("filePath") String filePath
                             ,HttpServletResponse response) throws IOException {
       log.info("filePath : " + filePath);

       // 파일 다운로드를 위한 헤더 세팅
       // - ContentType             : application/octet-stream
       // - Content-Disposition     : attachment; filename="파일명.확장자"
       File file = new File(filePath);
       String filename = file.getName();
       response.setContentType("application/octet-stream");
       response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");


       // 파일 입출력 세팅
       FileInputStream fis = new FileInputStream(file);
       ServletOutputStream sos = response.getOutputStream();

       // 다운로드
       FileCopyUtils.copy(fis, sos);    // 입력한 파일 출력

    }
}
