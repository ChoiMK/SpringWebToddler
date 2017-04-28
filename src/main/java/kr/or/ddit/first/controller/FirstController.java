package kr.or.ddit.first.controller;

import kr.or.ddit.vo.MemberVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost/SpringWebToddler/first/hello.first
@Controller
@RequestMapping("/first/")
public class FirstController implements ApplicationContextAware{
	private static Logger logger = LoggerFactory.getLogger(FirstController.class);
	
    @RequestMapping("hello.first")
	public String hello(){
    	logger.debug("/first/hello.first 호출");
		return "first/hello";
	}

	@Override
	public void setApplicationContext(ApplicationContext webApplicationContext)
			throws BeansException {
//		MemberVO memberInfo1 = (MemberVO) webApplicationContext.getBean("memberVO");
//		logger.debug("FirstController : memberInfo1 - " + memberInfo1);
	}
    
    
}







