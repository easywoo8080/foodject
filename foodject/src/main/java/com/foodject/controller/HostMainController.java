package com.foodject.controller;


import javax.servlet.http.HttpSession;

import com.foodject.biz.HostManagerBiz;
import com.foodject.biz.HostOrdersBiz;
import com.foodject.restapi.BcrytPassward;
import com.foodject.vo.HostManagerVO;
import com.foodject.vo.HostOrdersVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/host")
public class HostMainController {
	@Value("${kakaoJSKey}")
	String kakaoJSKey;
	
	@Autowired
	HostManagerBiz mbiz;
	@Autowired
	HostOrdersBiz obiz;
	
	@Autowired
	BcrytPassward bp;



	@RequestMapping("")
	public ModelAndView main(ModelAndView mv, HttpSession session) {
		HostManagerVO mng = (HostManagerVO) session.getAttribute("loginshop");
		if( mng == null ){
			mv.setViewName("/host/login");
			return mv;
		}

		int result = 0;
		HostOrdersVO ovo = new HostOrdersVO();
		ovo.setShop_mid(mng.getId());
		try {
			result = obiz.mainseletcstatus(mng.getId());
			mv.addObject("mainseletcstatus", result);
			result = obiz.mainallorders(mng.getId());
			mv.addObject("mainallorders", result);
			ovo = obiz.mainallpriceday(ovo);
			System.out.println("dayallprice"+ovo);
			mv.addObject("dayallprice", ovo);
			ovo = new HostOrdersVO();
			ovo.setShop_mid(mng.getId());
			ovo = obiz.mainallpricemonth(ovo);
			System.out.println("monthallprice"+ovo);
			mv.addObject("monthallprice", ovo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		mv.setViewName("host/index");
		
		mv.addObject("center", "host/center");
		mv.addObject("kakaosrc",kakaoJSKey);
		return mv;
	}
	
	@RequestMapping("/sample")
	public String sample(Model m) {

		return "host/sample";
	}
	@RequestMapping("/register")
	public ModelAndView register(ModelAndView mv) {
		mv.setViewName("host/register");
		return mv;
	}
	@RequestMapping("/registerimpl")
	public String registerimpl(Model m, HostManagerVO manager, HttpSession session) {
		
		try {
			//비밀번호 암호화 처리
			manager.setPwd(bp.hashPassward(manager.getPwd()));

			mbiz.register(manager);
			session.setAttribute("loginshop", manager);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "host/index";
	}
//	@RequestMapping("/mypage")
//	public ModelAndView mypage(ModelAndView mv) {
//		mv.setViewName("host/mypage");
//		return mv;
//	}
	@RequestMapping("/mypage")
	public String mypage(Model m, HttpSession session) {
		HostManagerVO mng = (HostManagerVO) session.getAttribute("loginshop");
		try {
			mng= mbiz.get(mng.getId());
			m.addAttribute("m", mng);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "host/mypage";
	}
	@RequestMapping("/update")
	public String update(Model m, HostManagerVO obj) {
		try {
			mbiz.modify(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:mypage?id=" + obj.getId();
	}
	@RequestMapping("/delete")
	public String updatests(Model m, HostManagerVO obj, HttpSession session ) {
		try {
			mbiz.modifysts(obj);
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "host/index";
	}
	@RequestMapping("/findpwd")
	public ModelAndView findpwd(ModelAndView mv) {
		
		mv.setViewName("host/findpwd");
		return mv;
	}
	@RequestMapping("/changepwd")
	public ModelAndView changepwd(ModelAndView mv, HttpSession session) {
		HostManagerVO mng = (HostManagerVO) session.getAttribute("loginshop");
		session.setAttribute("loginshop", mng);
		try {
			mng= mbiz.get(mng.getId());
			mv.addObject("m", mng);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("host/changepwd");
		return mv;
	}
	@RequestMapping("/changepwd2")
	public ModelAndView changepwd2(ModelAndView mv, HttpSession session) {
		HostManagerVO mng = (HostManagerVO) session.getAttribute("loginshop");
		session.setAttribute("loginshop", mng);
		try {
			mng= mbiz.get(mng.getId());
			mv.addObject("m", mng);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mv.setViewName("host/changepwd2");
		return mv;
	}
	@RequestMapping("/update2")
	public String update2(Model m, HostManagerVO obj) {
		obj.setPwd(bp.hashPassward(obj.getPwd()));
		try {
			
			mbiz.modifypwd(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:mypage";
	}
	
	
	@RequestMapping("/findpwd/findpwdimpl")
	public String findpwdimpl(Model m, String id, String email, String pwd) {
		HostManagerVO manager = null;
		try {
			manager = mbiz.get(manager.getId());
			System.out.println(manager);
			if(manager == null) {
				throw new Exception("아이디가 존재하지 않습니다.");
			}
			
			if (manager.getEmail().equals(email)) {
				throw new Exception(manager.getPwd());
				
			}else {
				throw new Exception("이메일이 틀립니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "host/login";
	}
	
	
}
