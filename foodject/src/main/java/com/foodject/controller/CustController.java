package com.foodject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cust")
public class CustController {
	
	public void mainProduct(Model m) {
//		List<ProductVO> plist = null;
//		String pimgpath = Paths.get(System.getProperty("user.dir"), "src", "main","resources","static","img", "product_img").toString();
//		System.out.println("imgpath : " +  pimgpath);
//		try {	
//			plist = mainbiz.get();
//			m.addAttribute("plist", plist);
//			m.addAttribute("imgpath", pimgpath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	@RequestMapping("")
	public ModelAndView cust(ModelAndView mv) {
		mv.setViewName("index");
		mv.addObject("center", "/cust/center" );
		return mv;
	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		return "/cust/login";
	}

	@RequestMapping("/impl")
	public String custimpl(Model m) {
		return "/cust/impl";
	}

}
