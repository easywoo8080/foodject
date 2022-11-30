package com.foodject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserErrorController {
	
	@Value("${kakaoJSKey}")
	String kakaoJSKey;
	
	@RequestMapping("/custUidError")
	public String custUid(Model m) {
		return "user/error/custuiderror";
	}
	
	@RequestMapping("/cartSidError")
	public String cartSid(Model m, int sid) {
		m.addAttribute("sid",sid);
		return "user/error/cartsiderror";
	}

	@RequestMapping("/pathError")
	public String pathError(Model m) {
		return "user/error/patherror";
	}
	
	@RequestMapping("/orderError")
	public String orderErrorError(Model m) {
		return "user/error/ordererror";
	}
}
