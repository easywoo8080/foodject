package com.foodject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodject.biz.UserCartBiz;
import com.foodject.vo.UserCartVO;

@RestController
@RequestMapping("/cart")
public class UserCartAJAX {
	
	@Autowired
	UserCartBiz crbiz;
	
	@RequestMapping("updateDeprice")
	public Object updateDeprice(int cartId, int deprice) {
		//System.out.println(cartId+" "+deprice);
		UserCartVO obj = null;
		try {
			obj = crbiz.get(cartId);
			obj.setDeprice(deprice);
			crbiz.modifyDeprice(obj);
			System.out.println(obj);
		} catch (Exception e) {
			return 1;
		}
		
		return 0;
	}
}
