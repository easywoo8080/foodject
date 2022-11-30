package com.foodject.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.biz.UserCustBiz;
import com.foodject.frame.Util;
import com.foodject.restapi.BcrytPassward;
import com.foodject.vo.AddrVO;
import com.foodject.vo.UserCustVO;



@Controller
@RequestMapping("/cust")
public class UserCustLoginController {
   
   @Autowired
   UserCustBiz custbiz;

   @Autowired
   Util ut;

   @Autowired
   BcrytPassward bp;
   
   @RequestMapping("/login")
   public String login(Model m, String msg, String prevUrl) {
      if(msg != null && msg.equals("f")) {
         m.addAttribute("msg", "!! 아이디 또는 비밀번호를 확인해주세요");
      }
      m.addAttribute("center","user/cust/login");
      if(prevUrl != null) {
         m.addAttribute("prevUrl",prevUrl);
      }
      
      return "user/index";
   }
   
   @RequestMapping("/loginimpl")
   public String loginimpl(Model m, String id, String pwd, HttpSession session, String prevUrl, HttpSession sessionAddr) {
      UserCustVO cust = null;
      AddrVO addrObj = (AddrVO) sessionAddr.getAttribute("addrObj");
      try {
         cust = custbiz.get(id);
         if(cust == null) {
            throw new Exception();
         }
         // 비밀번호 암호화 체크 처리
         if(bp.checkPassward(cust.getPwd(), pwd)) {
            session.setAttribute("loginid", cust);
         }else {
            throw new Exception();
         }

   
      } catch (Exception e) {
         return "redirect:/cust/login?msg=f&prevUrl="+prevUrl;
      }
      if(addrObj == null) {
    	System.out.println("sessionAddr is null");
      	return "redirect:/";
      }else {
    	  addrObj.setId(id);
    	  try {
			custbiz.modifyAddr(addrObj);
			System.out.println("주소 세션으로 회원정보 업데이트");
			cust = custbiz.get(id);
			session.setAttribute("loginid", cust);
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
    	  
    	  if(prevUrl != null && !(prevUrl.equals("null")) && !(prevUrl.equals(""))) {
	         return "redirect:"+prevUrl;
	      }else {
	         return "redirect:/";
	      }
      }
      
      
   }
   
   @RequestMapping("/logout")
   public String logout(HttpSession session) {
      if(session != null) {
         session.invalidate();
      }
      return "redirect:/";
   }

   @RequestMapping("/register")
   public String register(Model m) {
      return "user/cust/register";
   }
   
   @RequestMapping("/registerimpl")
   public String registerimpl(Model m, UserCustVO cust, String img) {
      //이미지파일 이름 저장
      String imgname = cust.getMf().getOriginalFilename();
      String[] splitname = imgname.split("[.]");
      String idname = cust.getId();
       String savename = idname + "." + splitname[splitname.length -1];

      // 비밀번호 암호화 처리
      cust.setPwd(bp.hashPassward(cust.getPwd()));
      if(savename.equals(idname+".")) {
         cust.setImg("icon.jpg");         
      }else {
         cust.setImg(savename);
         ut.saveFile(cust.getMf(), savename, "cust");
      }      
      
      try {
         custbiz.register(cust);
         // saveFile(실제 파일, 저장할 이름, 사용되는 DB 컬럼명)
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      return "redirect:/cust/login";

   }
   
}
   
