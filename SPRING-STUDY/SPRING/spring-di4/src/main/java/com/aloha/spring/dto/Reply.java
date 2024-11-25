package com.aloha.spring.dto;

import org.springframework.stereotype.Component;

@Component
public class Reply {

	private String title;
	private String writer;
	private String content;
	
	public Reply() {
		this.title = "제목없음";
		this.writer = "제목없음";
		this.content = "제목없음";
	}

	public Reply(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Reply [title=" + title + ", writer=" + writer + ", content=" + content + "]";
	}
}
