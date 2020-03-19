package com.spring.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

public class GetBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">>> 글 상세 조회 처리");
		//1. 전달 받은 값 추출
		String seq = request.getParameter("seq");
		
		//2. DB 연동처리(하나의 글 조회)
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);
		
//		//3. 검색결과를 세션에 저장하고 상세보기 화면으로 이동
//		HttpSession session = request.getSession();
//		session.setAttribute("board", board);
//		return "getBoard";
		
		//3. ModelAndView 형식으로 작성 후 리턴
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board); //데이타 저장
		mav.setViewName("getBoard.jsp");
		
		return mav;
	}
}










