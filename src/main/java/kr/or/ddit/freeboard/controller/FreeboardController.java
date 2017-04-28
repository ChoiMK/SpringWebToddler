package kr.or.ddit.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/freeboard/")
public class FreeboardController {
	
	// freeboard/freeboardForm   order 1  UrlBasedViewResolver(TilesView)
	//                           order 2  BeanNameViewResolver  X
	//                           order 3  InternalResourceViewResolver  O 타일즈 원함.
	@RequestMapping("freeboardForm")
	public void freeboardForm(){}
}
