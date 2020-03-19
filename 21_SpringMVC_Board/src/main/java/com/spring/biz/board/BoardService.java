package com.spring.biz.board;

import java.util.List;

public interface BoardService {
	//CRUD 기능 구현 메소드 정의
	void insertBoard(BoardVO vo); //입력
	void updateBoard(BoardVO vo); //수정
	void deleteBoard(BoardVO vo); //삭제
	BoardVO getBoard(BoardVO vo); //조회(상세조회)
	List<BoardVO> getBoardList(BoardVO vo); //조회(글목록)
}
