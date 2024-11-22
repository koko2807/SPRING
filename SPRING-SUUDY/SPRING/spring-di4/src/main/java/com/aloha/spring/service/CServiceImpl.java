package com.aloha.spring.service;

import org.springframework.stereotype.Service;

//서비스 빈으로 등록
//빈 등록 어노테이션("빈이름")
//* 빈이름을 지정하지 않으면 클래스 이름을 기본으로 지정
//* "빈이름"을 지정하면, @Qualifier("지정한빈이름") 으로 주입할 수 있다.
@Service("C") // 서비스 빈으로 등록
public class CServiceImpl implements MyService {

	 // 빈이름 지정하지 않으면, "AServiceImpl" 빈이름으로 지정
	
	@Override
	public void test() {
		System.out.println("CServiceImpl...");
}
}
