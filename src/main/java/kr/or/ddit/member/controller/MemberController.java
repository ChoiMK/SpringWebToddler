package kr.or.ddit.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/")
public class MemberController implements ServletContextAware{
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 생성자, 전역변수,setter : 빈으로 등록된 빈 클래스 중에서 타입이 일치하는 빈을 IoC(DI)
	@Autowired
	private IMemberService service;
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private MessageSourceAccessor msgAccessor;
	
	@Override
	public void setServletContext(ServletContext application) {
		// WAS에 ServletContext : application
	}

	// 컨트롤러 클래스 반환타입 : void(포워드) - 클라이언트의 요청 uri를 기초로 view를 결정
	//                          Model - 포워드 되는 view에 전달될 값을 저장(request scope)
	//                                  void와 동일하게 view를 요청 uri를 기초로 결정
	//                          ModelMap - 포워드 되는 view에 전달될 값을 저장(request scope)
	//                                  void와 동일하게 view를 요청 uri를 기초로 결정
	//                          String(포워드) - InternalResourceViewResolver에 전달될
	//                                     view의 이름 반환
	//                                     prefix - /WEB-INF/views/
	//                                              전달받은 view를 검색하는 기준 경로
	//                                     suffix - .jsp
	//                                              전달받은 view후위에 .jsp 추가로 view명 경로와
	//                                              이름 완성
	//                                   redirect: 리다이렉트 처리
	//                                   forward: 포워딩 처리
	//                          ModelAndView - 포워드 되는 view에 전달될 값을 저장(request scope)
	//                                         view의 경로와 이름 설정
	//                                         redirect: forward: 활용
	// 컨트롤러 메서드에 선언 : HttpServletRequest request
	//                        HttpServletResponse response
	//                        HttpSession session
	//                        Map<String, String>
	//                        Model
	//                        ModelMap
	//                        VO(커맨드 오브젝트)
	//                        ModelAndView
	// @RequestParam : 클라이언트로부터 요청시 반드시 전달되어야하는 파라메터를 설정.
	//                 @Required=true(default)
	// @PathVariable : 클라이언트로부터 요청시 uri에서 필요로하는 값 취득 활용.
	// @ResponseBody : 컨트롤러 메셔드에서 반환되는 값을 response의 출력 버퍼에 저장 후
	//                 요청처에 반환값 형식 그대로 전달.
	@RequestMapping("memberList")
	public ModelAndView memberList(Model model) throws Exception{
		// member/memberList
		List<MemberVO> memberList = service.getMemberList(new HashMap<String, String>());
		
		// message 프로퍼티스 파일의 키 접근으로 값을 취득.
		String message = msgAccessor.getMessage("fail.common.msg", Locale.KOREA);
		logger.debug(message);
		
//		Model model = new ExtendedModelMap();
//		model.addAttribute("memberList", memberList);
		
		ModelAndView andView = new ModelAndView();
		andView.addObject("memberList", memberList);
		andView.setViewName("member/memberList");
//		andView.setViewName("redirect:/join/loginForm.do");
//		andView.setViewName("forward:/join/loginForm.do");
		return andView;
	}
	
	// http://localhost/SpringWebToddler/member/memberView.do?mem_id=a001
	@RequestMapping("memberView")
	private ModelMap memberView(@RequestParam String mem_id, 
			@RequestParam(required=false, defaultValue="default") String mem_pass, ModelMap map, Map<String, String> params)
			throws Exception{
//		ModelMap map = new ModelMap();
//		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		MemberVO memberInfo = service.getMemberInfo(params);
		
		map.addAttribute("memberInfo", memberInfo);
		
		return map;
	}
	
	// 클라이언트 요청시 요청 헤더 정보 취득.
	@RequestMapping("updateMemberInfo")
	public String updateMember(MemberVO memberInfo,
			@RequestHeader("User-Agent") String userAgent,
			@RequestHeader("Accept-Language") String acceptLanguage) throws Exception{
		service.updateMemberInfo(memberInfo);
		
		logger.debug("가나다 {} : {}", userAgent, acceptLanguage);
		
		return "redirect:/member/memberList.do";
	}
	
	// /member/deleteMemberInfo/a001
	@RequestMapping("deleteMemberInfo/{mem_id}")
	public String deleteMember(@PathVariable String mem_id,
			Map<String, String> params) throws Exception{
		params.put("mem_id", mem_id);
		
		service.deleteMemberInfo(params);
		
		return "redirect:/member/memberList.do";
	}
	
	@RequestMapping("memberForm")
	public void memberForm(){}
	
	@RequestMapping("insertMemberInfo")
	public String insertMember(MemberVO memberInfo) throws Exception{
		service.insertMemberInfo(memberInfo);
		String message = "";
		try {
			message = URLEncoder.encode("회원 가입이 완료되었습니다. 로그인해주세요.", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "redirect:/join/loginForm.do?message="+message;
	}
	
	@RequestMapping(value="idCheck", method=RequestMethod.POST)
	@ResponseBody
	public String idCheck1(String mem_id, Map<String, String> params) throws Exception{
		params.put("mem_id", mem_id);
		
		MemberVO memberInfo = service.getMemberInfo(params);
		
		Map<String, String> jsonMap = new HashMap<String, String>();
		if(memberInfo == null){
			jsonMap.put("flag", "true");
		}else{
			jsonMap.put("flag", "false");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(jsonMap);
		// {"flag":"true"}
		return jsonData;
	}
	
	@RequestMapping(value="idCheck", method=RequestMethod.GET)
	public ModelAndView idCheck2(String mem_id, Map<String, String> params,
			ModelAndView andView) throws Exception {
		params.put("mem_id", mem_id);
		
		MemberVO memberInfo = service.getMemberInfo(params);
		
		if(memberInfo == null){
			andView.addObject("flag", "true");
		}else{
			andView.addObject("flag", "false");
		}
		// 스프링의 AbstractView를 extends한 클래스를 view로 활용.
		// class MappingJackson2JsonView extends AbstractView{}
		andView.setViewName("jsonConvertView");
		return andView;
	}
}













