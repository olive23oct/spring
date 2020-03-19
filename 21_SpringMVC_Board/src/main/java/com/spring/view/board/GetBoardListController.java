package com.spring.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

public class GetBoardListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">>> 글 목록 조회 처리");
		//1. 전달받은 값 추출(전달 받은 값 없어 생략)
		//2. DB연동 처리
		BoardVO vo = new BoardVO();

		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
/*		
		//3. 화면네비게이션 처리
		//3-1. 목록화면에서 사용할 데이타 세션 저장
		request.getSession().setAttribute("boardList", boardList);
		
		//response.sendRedirect("getBoardList.jsp");
		//3-2. 목록화면으로 이동
		return "getBoardList";
*/		
		
		//3. ModelAndView 형식으로 작성후 리턴
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.setViewName("getBoardList.jsp");
		
		return mav;
	}
}










