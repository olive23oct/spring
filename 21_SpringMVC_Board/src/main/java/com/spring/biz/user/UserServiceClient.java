package com.spring.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

	public static void main(String[] args) {
		//1. 스프링 컨테이너 기동
		AbstractApplicationContext container
			= new GenericXmlApplicationContext("applicationContext.xml");
		System.out.println("---- 스프링컨테이너 기동 후---");
		
		//2. 객체 사용
		//2-1. 컨테이너로부터 UserServiceImpl 객체 가져오기(Lookup)
		UserService userService 
			= (UserService)container.getBean("userService");
		
		//2-2. 사용자 조회
		UserVO vo = new UserVO();
		vo.setId("user1");
		vo.setPassword("user11111");
		
		UserVO user = userService.getUser(vo);
		//System.out.println("검색된 user: " + user);
		if (user != null) {
			System.out.println("[로그인성공] "
					+ user.getName() + "님 환영합니다.");
		} else {
			System.out.println("[로그인실패] "
					+ "존재하지 않는 사용자입니다.");
		}

		//3. 컨테이너 닫기
		container.close();
	}

}
