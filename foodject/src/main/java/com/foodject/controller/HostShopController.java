package com.foodject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.foodject.biz.HostOrdersBiz;
import com.foodject.biz.HostShopBiz;
import com.foodject.frame.Util;
import com.foodject.vo.HostManagerVO;
import com.foodject.vo.HostOrdersVO;
import com.foodject.vo.HostShopVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/host/shop")
public class HostShopController {

	@Value("${kakaoJSKey}")
	String kakaoJSKey;
	@Autowired
	HostShopBiz biz;	
	@Autowired
	HostOrdersBiz obiz;
	@Autowired
	Util ut;
	
	@RequestMapping("bills")
	public ModelAndView bills(ModelAndView mv, HttpSession session, int id) {

		List<HostOrdersVO> list = null;
		if (session.getAttribute("loginshop") == null) {
			mv.setViewName("redirect:/host");
			return mv;
		}
		try {
			list = obiz.selectbills(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() != 0) {
			mv.addObject("olist", list);
		}

		mv.addObject("kakaosrc",kakaoJSKey);
		mv.setViewName("/host/index");
		mv.addObject("contents", "/host/shop/bills");
		mv.addObject("center", "/host/shop/analysis");
		return mv;
	}
	@RequestMapping("billcharts")
	public ModelAndView billcharts(ModelAndView mv, HttpSession session) {

		HostManagerVO manager = null;
		List<HostShopVO> list = null;
		if (session.getAttribute("loginshop") == null) {
			mv.setViewName("redirect:/host");
			return mv;
		}
		manager = (HostManagerVO) session.getAttribute("loginshop");
		String mid = manager.getId();
		try {
			list = biz.getmid(mid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() != 0) {
			mv.addObject("slist", list);
		}

		mv.addObject("kakaosrc",kakaoJSKey);
		mv.setViewName("/host/index");
		mv.addObject("contents", "/host/shop/billcharts");
		mv.addObject("center", "/host/shop/analysis");
		return mv;
	}
	


	@RequestMapping("analysis")
	public ModelAndView analysis(ModelAndView mv, HttpSession session) {

		HostManagerVO manager = null;
		List<HostShopVO> list = null;
		if (session.getAttribute("loginshop") == null) {
			mv.setViewName("redirect:/host");
			return mv;
		}
		manager = (HostManagerVO) session.getAttribute("loginshop");
		String mid = manager.getId();
		try {
			list = biz.getmid(mid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() != 0) {
			mv.addObject("slist", list);
		}
		
		mv.addObject("kakaosrc",kakaoJSKey);
		mv.setViewName("/host/index");
		mv.addObject("center", "/host/shop/analysis");
		return mv;
	}
	@RequestMapping("")
	public ModelAndView shop(ModelAndView mv, HttpSession session) {
		HostManagerVO manager = null;
		List<HostShopVO> list = null;
		if (session.getAttribute("loginshop") == null) {
			mv.setViewName("redirect:/host");
			return mv;
		}
		manager = (HostManagerVO) session.getAttribute("loginshop");
		String mid = manager.getId();
		try {
			list = biz.getmid(mid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() != 0) {
			mv.addObject("slist", list);
		}
		
		mv.addObject("kakaosrc",kakaoJSKey);
		mv.setViewName("/host/index");
		mv.addObject("center", "/host/shop/center");
		return mv;
	}

	@RequestMapping("/shopregister")
	public ModelAndView register(ModelAndView mv) {
		mv.setViewName("/host/index");
		mv.addObject("kakaosrc",kakaoJSKey);
		mv.addObject("center", "/host/shop/register");
		return mv;
	}

	@RequestMapping("/shopregisterimpl")
	public ModelAndView registerimpl(ModelAndView mv, HostShopVO obj, HttpSession session) {
		HostManagerVO manager = null;
		manager = (HostManagerVO) session.getAttribute("loginshop");
		obj.setMid(manager.getId());
		obj.setStatus(1);
		// ?????.jpg
		// System.out.println("latt : " + latt);
		// System.out.println("logt : " + logt);
		try {
			biz.registerMarker(obj);		
			biz.registerShop(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String imgname = obj.getMf().getOriginalFilename();
		String[] splitname = imgname.split("[.]");
		String idname = obj.getId();
		String savename = idname + "." + splitname[splitname.length - 1];

		if(savename.equals(idname+".")) {
			obj.setLogo("icon.jpg");			
		}else {
			obj.setLogo(savename);
			try {
				// saveFile(실제 파일, 저장할 이름, 사용되는 DB 컬럼명)
				biz.modify(obj);
				
				ut.saveFile(obj.getMf(), savename, "shop");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}	
		mv.setViewName("redirect:/host/shop");
		mv.addObject("center", "/host/shop/center");

		return mv;
	}

	@RequestMapping("/orderlist")
	public ModelAndView orderlist(ModelAndView mv) {
		mv.setViewName("/host/index");
		mv.addObject("center", "/host/shop/orderlist");
		return mv;
	}

	@RequestMapping("/shopadd")
	public ModelAndView shopadd(ModelAndView mv) {
		mv.setViewName("/host/index");
		mv.addObject("center", "/host/shop/shopadd");
		return mv;
	}
}
