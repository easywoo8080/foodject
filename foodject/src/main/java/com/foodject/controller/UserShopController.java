package com.foodject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.biz.UserShopBiz;
import com.foodject.vo.MarkerVO;
import com.foodject.vo.UserShopVO;

@Controller
@RequestMapping("/shop")
public class UserShopController {
	
	@Autowired
	UserShopBiz sbiz;  
	
	
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
	public String main(Model m, int cid, double latt, double logt) {
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
	
	
	

}
