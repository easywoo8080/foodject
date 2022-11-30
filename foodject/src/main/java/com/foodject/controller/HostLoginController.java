package com.foodject.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foodject.biz.HostManagerBiz;
import com.foodject.restapi.BcrytPassward;
import com.foodject.vo.HostManagerVO;

@Controller
@RequestMapping("/host/login")
public class HostLoginController {
	@Autowired
	HostManagerBiz biz;

	@Autowired
	BcrytPassward bp;

	
	


	@RequestMapping("")
	public ModelAndView main(ModelAndView mv, String msg) {
		if(msg != null) {
			msg.split(":");
			String m[] = msg.split(":");
			mv.addObject("msg", m[m.length-1]);
		}
		mv.setViewName("host/login");
//		mv.addObject("center", "login" );
		return mv;
	}
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {
		HostManagerVO manager = null;
		try {
			
			manager = biz.get(id);			
			if(manager == null) {
				
				throw new Exception("아이디가 존재하지 않습니다.");
				
			}
			// if (bp.checkPassward(manager.getPwd(), pwd)) {
			// 	session.setAttribute("loginshop", manager);
				
			// }else {
			// 	throw new Exception("비밀번호가 틀립니다.");
			// }

			// 비밀번호 암호화 체크 처리
			if (bp.checkPassward(manager.getPwd(), pwd)) {
				session.setAttribute("loginshop", manager);
				
			}else {
				throw new Exception("비밀번호가 틀립니다.");
			}
		} catch (Exception e) {
			String msg = e.toString();
			try {
				msg = URLEncoder.encode(msg, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "redirect:/host/login?msg=" + msg; //? 뒤에값을 서버프로그램에 전송한다
		}
		return "redirect:/host/";
	}
	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "host/index";
	}


}
