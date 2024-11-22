package com.aloha.spring.dao;

import org.springframework.stereotype.Repository;

@Repository		// DAO 역할로 빈 등록
public class ReplyDAO extends BoardDAO {

	public void test() {
		System.out.println("ReplyDAO...");
	}
}
