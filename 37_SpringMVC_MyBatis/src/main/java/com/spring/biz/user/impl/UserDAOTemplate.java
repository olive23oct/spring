package com.spring.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.biz.user.UserVO;

//(실습) JdbcTemplate 필드주입해서 CRUD 구현
//UserServiceImpl 클래스에 적용 테스트
@Repository("userDAOTemplate")
public class UserDAOTemplate {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//SQL 명령어들
	private final String USER_GET = "SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
	private final String USER_LIST = "SELECT * FROM USERS ORDER BY USERID";
	
	private final String USER_INSERT = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
	private final String USER_UPDATE = "UPDATE USERS SET PASSWORD = ? WHERE ID = ?";
	private final String USER_DELETE = "DELETE FROM USERS WHERE ID = ? ";
	
	//CRUD 기능의 메소드 구현
	//회원 조회
	public UserVO getUser(UserVO vo) {
		System.out.println("===>Spring JDBC로 getUser() 기능 처리");
		Object[] args = {vo.getId(), vo.getPassword()};
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}
	
	public void insertUser(UserVO vo) {
		System.out.println("===>Spring JDBC로 insertUser() 기능 처리");
		Object[] args = {vo.getId(), vo.getPassword(), vo.getName(), vo.getRole()};
		jdbcTemplate.update(USER_INSERT, args);
	}
	
	public void updateUser(UserVO vo) {
		System.out.println("===>Spring JDBC로 updateUser() 기능 처리");
		Object[] args = {vo.getPassword(), vo.getId()};
		jdbcTemplate.update(USER_UPDATE, args);
	}
	
	public void deleteUser(UserVO vo) {
		System.out.println("===>Spring JDBC로 deleteUser() 기능 처리");
		Object[] args = {vo.getId()};
		jdbcTemplate.update(USER_DELETE, args);
	}
	
	public List<UserVO> getUserList(UserVO vo) {
		System.out.println("===>Spring JDBC로 getUserList() 기능 처리");
		return jdbcTemplate.query(USER_LIST, new UserRowMapper());
	}
}
