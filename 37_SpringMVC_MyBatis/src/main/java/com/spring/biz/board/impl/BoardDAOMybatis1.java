package com.spring.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

@Repository("BoardDAOMybatis1")
public class BoardDAOMybatis1 extends SqlSessionDaoSupport {
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public BoardDAOMybatis1() {
		System.out.println(">> BoardDAOMybastis1 객체 생성");
	}
	//글 입력(INSERT)
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 insertBoard() 실행");
		super.getSqlSession().insert("BoardDAO.insertBoard", vo);
	}
	
	//글수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 updateBoard() 실행");
		super.getSqlSession().update("BoardDAO.updateBoard", vo);
	}
	
	//글삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 deleteBoard() 실행");
		super.getSqlSession().delete("BoardDAO.deleteBoard", vo);
	}
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 getBoard() 실행");
		return getSqlSession().selectOne("BoardDAO.getBoard", vo);
	}
	
	//검색조건 적용해서 데이타 조회
	//(실습) 검색조건 적용해서 처리되도록 구현
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Mybatis로 getBoardList() 실행");
		return getSqlSession().selectList("BoardDAO.getBoardList", vo);
	}	
}



















