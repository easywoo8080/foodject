package com.foodject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.biz.UserCartBiz;
import com.foodject.biz.UserCollectionBiz;
import com.foodject.biz.UserCustBiz;
import com.foodject.biz.UserMenuBiz;
import com.foodject.biz.UserOptBiz;
import com.foodject.biz.UserOptcartBiz;
import com.foodject.biz.UserShopBiz;
import com.foodject.vo.MarkerVO;
import com.foodject.vo.UserCartVO;
import com.foodject.vo.UserCollectionVO;
import com.foodject.vo.UserCustVO;
import com.foodject.vo.UserMenuVO;
import com.foodject.vo.UserOptVO;
import com.foodject.vo.UserOptcartVO;
import com.foodject.vo.UserShopVO;

@Controller
@RequestMapping("/shop")
public class UserShopController {
	
	@Autowired
	UserCustBiz csbiz;
	
	@Autowired
	UserShopBiz sbiz;  
	
	@Autowired
	UserMenuBiz mnbiz;
	
	@Autowired
	UserCollectionBiz cbiz;
	
	@Autowired
	UserOptBiz obiz;
	
	@Autowired
	UserOptcartBiz ocbiz;
	
	@Autowired
	UserCartBiz crbiz;
	


	@RequestMapping("")
	public String shop(Model m, int cid, double latt, double logt) {
		MarkerVO obj = new MarkerVO(latt,logt,cid);
		List<UserShopVO> list = null;

		try {	
			
			list = sbiz.getMain(obj);
			m.addAttribute("shoplist",list);
			m.addAttribute("center", "user/shop/center");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "user/index";
	}
	
	
	@RequestMapping("/main")
	public String main(Model m, int sid, HttpSession session) {
		List<UserMenuVO> mlist = null;
		List<UserCollectionVO> clist = null;
		List<UserOptVO> olist = null;
		UserShopVO obj = null;
		List<UserCartVO> crlist= null;
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		int row = 0;
		
		
		try {
			m.addAttribute("sid",sid);
			
			obj = sbiz.get(sid);
			m.addAttribute("shop",obj);
			
			clist = cbiz.get_byShop(sid);
			m.addAttribute("clist",clist);
			
			mlist = mnbiz.get_byShop(sid);
			m.addAttribute("mlist",mlist);
			
			olist = obiz.get_byShop(sid);
			m.addAttribute("olist",olist);
			if(cust != null) {
				String uid = cust.getId();
				crlist = crbiz.get_byUid(new UserCartVO(0,uid,sid));
				row = crlist.size();
				// System.out.println(row);
				m.addAttribute("row",row);
			}else {
				System.out.println("Login session is null");
			}
			m.addAttribute("center","/user/shop/main");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute(mlist);
		return "user/index";
	}
	
	@RequestMapping("/addOptcart")
	public String addOptcart(Model m, int sid, int cartId ,int[] option, HttpSession session) {
		if(cartId == 0) {
			System.out.println("cartId is null");
			return "redirect:/";
		}
		
		
		System.out.println("Enter java");
		try {
			for (int i : option) {
				ocbiz.register(new UserOptcartVO(cartId,i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return "redirect:/shop/main?sid="+sid;
	}
	
	
	
}
