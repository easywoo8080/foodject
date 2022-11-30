package com.foodject.controller;


import javax.servlet.http.HttpSession;

import com.foodject.biz.UserCustBiz;
import com.foodject.restapi.BcrytPassward;
import com.foodject.vo.UserCustVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCustAJAX {

	@Autowired
	UserCustBiz custbiz;	
	
	@Autowired
   	BcrytPassward bp;

	@RequestMapping("/checkid")
	public String custNum(String id) {
		String result = "";
		UserCustVO cust = null;
		if(id.equals("")|| id==null) {
			return "1";
		}
		
		try {
			cust = custbiz.get(id);
			if(cust==null && id.length()>3) {
				result="0";
			}else {
				result="1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return result;
	}
	
	@RequestMapping("/checkpwdbp")
	public String checkpwdbp(HttpSession session, String pwd) {
		UserCustVO vo = (UserCustVO) session.getAttribute("loginid");
		String result = "";
		UserCustVO cust = null;
		if(pwd.equals("")|| pwd==null) {
			return "1";
		}
		System.out.println("pwd : " +  pwd);
		try {
			cust = custbiz.get(vo.getId());
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
			result="0";
		}else {
			result="1";
		}
		return result;
	}
	
	@RequestMapping("/checkpwd")
	public String checkpwd(HttpSession session, String pwd) {
		UserCustVO vo = (UserCustVO) session.getAttribute("loginid");
		String result = "";
		UserCustVO cust = null;
		if(pwd.equals("")|| pwd==null) {
			return "1";
		}
		System.out.println("pwd : " +  pwd);
		try {
			cust = custbiz.get(vo.getId());
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
			result="0";
		}else {
			result="";
		}


		return result;
	}


}

