package com.foodject.controller;


import javax.servlet.http.HttpSession;

import com.foodject.biz.HostManagerBiz;
import com.foodject.biz.HostMenuBiz;
import com.foodject.restapi.BcrytPassward;
import com.foodject.vo.HostManagerVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/host")
public class HostMainAJAX {

	@Autowired
	HostManagerBiz mngbiz;
	
	@Autowired
	HostMenuBiz mnbiz;

	@Autowired
	BcrytPassward bp;

	@RequestMapping("/login/checkpwdbphost")
	public String checkpwdbphost(HttpSession session, String pwd) {
		HostManagerVO vo = (HostManagerVO) session.getAttribute("loginshop");
		String result = "";
		HostManagerVO cust = null;
		if(pwd.equals("") || pwd==null) {
			return "1";
		}
		System.out.println("pwd : " +  pwd);
		try {
			cust = mngbiz.get(vo.getId());
			if(cust==null && pwd.length()>4) {
				result="0";
			}else {
				result="1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(cust.getPwd() + " :checkpwd: "  + pwd);
		if(bp.checkPassward(cust.getPwd(), pwd)) {
			System.out.println("비밀번호 일치");
			result="0";
		}else {
			System.out.println("비밀번호 불일치");
			result="1";
		}

		System.out.println("리턴 위" + result);
		return result;
	}

	
	@RequestMapping("mainNum")
	public String mainNum(Model m, HttpSession session,  int cartid, int num) {
		String result = "";
//		CartVO cv = null;
//		CartVO cart = new CartVO(cartid, num);
//		try {
//			cartbiz.modify(cart);
//			cv = cartbiz.get(cartid);
//			result = Integer.toString(cv.getPrice());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return result;
	}
//	@RequestMapping("/registerimpl")
//	public String registerimpl(Model m, HostManagerVO manager, HttpSession session) {
//		
//		try {
//			mngbiz.register(manager);
//			session.setAttribute("loginshop", manager);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		return "host/index";
//	}
	
	@RequestMapping("checkid")
	public String checkid(String id) {
		String result = "";
		HostManagerVO manager = null;
		
		if(id.equals("") || id == null) {
			return "1";
		}
		
		try {
			manager = mngbiz.get(id);
			if(manager == null  && id.length()>3 ) {
				result = "0";
			}else {
				result = "1";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
//	@RequestMapping("mnprice")
//	public String mnprice(Model m,  int id, int price) {
//		String result = "";
//		HostMenuVO mn = null;
//		HostMenuVO menu = new HostMenuVO(id, price);
//		try {
//			mnbiz.modify(menu);
//			mn = mnbiz.get(id);
//			result = Integer.toString(mn.getPrice());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return result;
//	}

}

