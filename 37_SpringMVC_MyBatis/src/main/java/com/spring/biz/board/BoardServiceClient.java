package com.spring.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {

	public static void main(String[] args) {
		//1. 스프링컨테이너 구동
		AbstractApplicationContext container 
			= new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. 스프링컨테이너에서 생성한 객체 요청(Lookup)
		//Board 테이블에 데이타 입력, 수정, 삭제, 조회
		BoardService boardService 
			= (BoardService)container.getBean("boardService");
		
		//2-1 입력
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트3");
		vo.setWriter("홍길동");
		vo.setContent("테스트3 작성 내용");
		
		boardService.insertBoard(vo);
		
		//2-2 수정
//		vo.setSeq(2);
//		vo.setTitle("테스트2-수정");
//		vo.setContent("테스트2 내용-수정");
//		boardService.updateBoard(vo);
		
		//2-3 삭제
//		vo.setSeq(3);
//		boardService.deleteBoard(vo);
		
		//2-4 목록조회
		System.out.println("===================");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for (BoardVO board : boardList) {
			System.out.println(board.toString());
		}
		
		//3. 스프링컨테이너 종료(close)
		container.close();

	}

}











