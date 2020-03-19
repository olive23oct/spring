package com.spring.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

//@Repository("boardDAOSpring")
public class BoardDAOSpring {

	@Autowired //스프링에서 제공하는 JdbcTemplate 객체 필드 주입
	private JdbcTemplate jdbcTemplate;
	
	//SQL문
	private final String BOARD_INSERT
		= "INSERT INTO BOARD (SEQ, TITLE, WRITER, CONTENT) "
		+ "VALUES ((SELECT NVL(MAX(SEQ),0) + 1 FROM BOARD), ?, ?, ?)";
	private final String BOARD_UPDATE
		= "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
	private final String BOARD_DELETE
		= "DELETE FROM BOARD WHERE SEQ = ?";
	private final String BOARD_GET
		= "SELECT * FROM BOARD WHERE SEQ = ?";
	private final String BOARD_LIST
		= "SELECT * FROM BOARD ORDER BY SEQ DESC";

	//TITLE 값으로 조회
	private final String BOARD_LIST_T
		= "SELECT * FROM BOARD "
		+ " WHERE TITLE LIKE '%'|| ? ||'%' "
		+ " ORDER BY SEQ DESC";
	
	//CONTENT 값으로 조회
	private final String BOARD_LIST_C
		= "SELECT * FROM BOARD "
		+ " WHERE CONTENT LIKE '%'|| ? ||'%' "
		+ " ORDER BY SEQ DESC";	
	
	public BoardDAOSpring() {
		System.out.println(">> BoardDAO 객체 생성");
	}
	//글 입력(INSERT)
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 실행");
		
//		jdbcTemplate.update(BOARD_INSERT, 
//				vo.getTitle(), vo.getWriter(), vo.getContent());
		
		//Object[] 전달 방식
		Object[] args = {vo.getTitle(), vo.getWriter(), vo.getContent()};
		jdbcTemplate.update(BOARD_INSERT, args);		
	}
	
	//글수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 실행");
		jdbcTemplate.update(BOARD_UPDATE, 
				vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	//글삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 deleteBoard() 실행");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 실행");
		//queryForObject("sql문", sql문매칭값(Object배열), 1개 row 저장데이타타입)
		Object[] args = {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
//	//전체 데이타 조회
//	public List<BoardVO> getBoardList(BoardVO vo) {
//		System.out.println("===> Spring JDBC로 getBoardList() 실행");
//		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
//	}
	
	//검색조건 적용해서 데이타 조회
	//(실습) 검색조건 적용해서 처리되도록 구현
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoardList() 실행");
		
		Object[] args = {vo.getSearchKeyword()};
		String sql = "";
		if (vo.getSearchCondition().equals("TITLE")) {
			sql = BOARD_LIST_T;
		} else if (vo.getSearchCondition().equals("CONTENT")) {
			sql = BOARD_LIST_C;
		}
		return jdbcTemplate.query(sql, args, new BoardRowMapper());
	}	
}



















