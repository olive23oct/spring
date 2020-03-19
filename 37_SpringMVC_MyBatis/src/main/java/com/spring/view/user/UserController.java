package com.spring.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.user.UserService;
import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST) 
	public String login(UserVO vo, HttpSession session) {//UserVO : Command 객체
		System.out.println(">> 로그인 처리");
		System.out.println("전달받은 vo: " + vo);
		
		//예외처리를 위해 예외 발생 시키기
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException(
					"아이디는 반드시 입력해야 합니다.");
		}
		
		UserVO user = userService.getUser(vo);

		if (user != null) { //사용자가 존재하는 경우
			session.setAttribute("userName", user.getName());
			return "redirect:getBoardList.do";
		} else { //사용자가 없는 경우
			return "login.jsp";
		}
	}
	
	//@ModelAttribute : 모델의 속성값으로 지정(속성명 별도 지정)
	//별도 명칭 부여 안하면 데이타 타입의 첫글자 소문자로 된 명칭 사용
	//method=RequestMethod.GET or POST : 요청방식에 따른 요청 처리
	@RequestMapping(value="/login.do", method=RequestMethod.GET) 
	public String loginView(@ModelAttribute("user")UserVO vo) {
		System.out.println(">> 로그인 화면으로 이동 - loginView()");
		vo.setId("test");
		vo.setPassword("test");
		
		return "login.jsp";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println(">>> 로그아웃 처리 - logout()");
		
		session.invalidate();
		return "login.jsp";
	}	
}










