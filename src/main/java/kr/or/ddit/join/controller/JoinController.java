package kr.or.ddit.join.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// http://localhost/SpringWebToddler/join/loginForm.do
// http://localhost/SpringWebToddler/join/loginCheck.do
// http://localhost/SpringWebToddler/join/logout.do
// <bean name="joinController" class="kr.or.ddit.join.controller.JoinController"/>
@Controller
@RequestMapping("/join/")
//public class JoinController implements ApplicationContextAware{
public class JoinController{
//	@Value("${driver}")
	@Value("#{jdbcConnection['driver']}")
	private String driver;
	@Value("#{jdbcConnection['url']}")
	private String url;
	@Value("#{jdbcConnection['username']}")
	private String username;
	@Value("#{jdbcConnection['password']}")
	private String pasword;
	
//	@Resource
	@Autowired
	public IMemberService service;
	private static Logger logger = LoggerFactory.getLogger(JoinController.class);
	
//	public JoinController(IMemberService service) {
//		this.service = service;
//		logger.debug("JoinController의 생성자");
//	}
	
//	@Resource
//	public void setMemberService(IMemberService service){
//		this.service = service;
//	}
	
//	private ApplicationContext context;
//	@Override
//	public void setApplicationContext(ApplicationContext context)
//			throws BeansException {
//		this.context = context;
//	}

	@RequestMapping("loginForm.do")
	public String loginForm(){
		return "join/loginForm";
	}
	
	// http://localhost/SpringWebToddler/loginCheck.do
	//     mem_id=a001&mem_pass=asdfasdf
	@RequestMapping(value="loginCheck.do", params={"mem_id=a001", "mem_pass=asdfasdf"}, method=RequestMethod.POST)
	public String loginCheck(String mem_id, String mem_pass,
			HttpSession session, 
			HttpServletRequest request,
			HttpServletResponse response,
			HashMap<String, String> params)
	        throws Exception{
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);

//		IMemberService service = context.getBean("memberService", IMemberService.class);
		MemberVO memberInfo = service.getMemberInfo(params);
		
		if(memberInfo == null){
			String message = "";
			try {
				message = URLEncoder.encode("회원이 아닙니다.", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "redirect:/join/loginForm.do?message="+message;
		}else{
			session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
			return "forward:/member/memberList.do";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/join/loginForm.do";
	}
}















