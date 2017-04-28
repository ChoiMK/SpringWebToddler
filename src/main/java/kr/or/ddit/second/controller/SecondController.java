package kr.or.ddit.second.controller;

import kr.or.ddit.vo.MemberVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost/SpringWebToddler/second/hello.second
@Controller
@RequestMapping("/second/")
public class SecondController implements ApplicationContextAware{
	private static Logger logger = LoggerFactory.getLogger(SecondController.class);
	
	// 컨트롤러 메서드의 반환값이 void일때에는 클라이언트의 요청시 uri를 참고해서 view의 이름을 결정.
	// void : second/hello
	@RequestMapping("hello.second")
	public void hello(){}

	@Override
	public void setApplicationContext(ApplicationContext webApplicationContext)
			throws BeansException {
		// 설정파일 내에 선언된 빈 클래스에 설정파일 내 기타 빈의 인스턴스를 취득하기위한
		// ApplicationContext 주입.
//		MemberVO memberInfo1 = (MemberVO) webApplicationContext.getBean("memberVO");
//		logger.debug("SecondController : memberInfo1 - " + memberInfo1);
//		logger.debug("{} : {}", "mem_id", memberInfo1.getMem_id());
//		logger.debug("{} : {}", "mem_pass", memberInfo1.getMem_pass());
//		MemberVO memberInfo2 = (MemberVO) webApplicationContext.getBean("memberVO");
//		logger.debug("SecondController : memberInfo2 - " + memberInfo2);
	}
	
	
}
