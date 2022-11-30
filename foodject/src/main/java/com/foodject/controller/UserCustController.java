package com.foodject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.biz.UserCustBiz;
import com.foodject.biz.UserOrdersBiz;
import com.foodject.frame.Util;
import com.foodject.restapi.BcrytPassward;
import com.foodject.vo.UserCustVO;
import com.foodject.vo.UserOrdersMyVO;

@Controller
@RequestMapping("/cust")
public class UserCustController {

	@Autowired
	UserCustBiz custbiz;

	@Autowired
	UserOrdersBiz ordersbiz;

	@Autowired
	Util ut;

	@Autowired
	BcrytPassward bp;

	@RequestMapping("")
	public String cust(Model m, String uid, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		if (cust == null) {
			return "redirect:/cust/login?prevUrl=" + prevUrl;
		} else if (cust.getId().equals(uid) == false) {
			return "redirect:/custUidError";
		} else {
			try {
				cust = custbiz.get(cust.getId());
				m.addAttribute("c", cust);
			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("center", "/user/cust/mypage");
			return "user/index";
		}
	}

	@RequestMapping("/update")
	public String update(Model m, String uid, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		if (cust == null) {
			return "redirect:/cust/login?prevUrl=" + prevUrl;
		} else if (cust.getId().equals(uid) == false) {
			return "redirect:/custUidError";
		} else {
			try {
				cust = custbiz.get(cust.getId());
				m.addAttribute("c", cust);
			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("center", "/user/cust/update");
		}
		return "user/index";
	}

	@RequestMapping("/updateimg")
	public String updateimg(Model m, UserCustVO cust, String img, String id) {
		String imgname = cust.getMf().getOriginalFilename();
		String[] splitname = imgname.split("[.]");
		String idname = cust.getId();
		String savename = idname + "." + splitname[splitname.length - 1];

		System.out.println("test" + cust.getId());
		System.out.println("test" + cust.getMf().getOriginalFilename());

		if (cust.getMf().getOriginalFilename().equals("")) {
			System.out.println(cust.getMf());
			System.out.println(cust.getImg());
		} else {
			cust.setImg(savename);
			ut.saveFile(cust.getMf(), savename, "cust");
		}
		try {
			custbiz.modifyimg(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cust/update?uid=" + id;
	}

	@RequestMapping("/updateetc")
	public String updateetc(Model m, UserCustVO cust, String id) {

		try {
			custbiz.modifyetc(cust);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/cust/update?uid=" + id;
	}

	@RequestMapping("/updatepwd")
	public String updatepwd(Model m, UserCustVO cust, HttpSession session, String id) {
		cust.setPwd(bp.hashPassward(cust.getPwd()));
		try {
			custbiz.modifypwd(cust);
			if (session != null) {
				session.invalidate();
			}
			return "redirect:/cust/login";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/cust/update?uid=" + id;
	}

	@RequestMapping("/delete")
	public String updatests(String id, HttpSession session, Model m) {
		System.out.println("dddd");
		try {
			custbiz.modifysts(id);
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@RequestMapping("/cs")
	public String cs(Model m) {
		m.addAttribute("chatbot", "/user/cust/chatbot");
		m.addAttribute("center", "/user/cust/cs");
		return "user/index";
	}

	@RequestMapping("/myorders")
	public String myorders(HttpSession session, String uid, String prevUrl, Model m) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		List<UserOrdersMyVO> olist = null;
		Integer count = null;

		if (cust == null) {
			return "redirect:/cust/login?prevUrl=" + prevUrl;
		} else if (cust.getId().equals(uid) == false) {
			return "redirect:/custUidError";
		} else {

			try {
				olist = ordersbiz.getod(cust.getId());
				count = ordersbiz.getcount(cust.getId());
				m.addAttribute("olist", olist);
				m.addAttribute("count", count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("center", "/user/cust/myorders");
		}
		return "user/index";
	}

	@RequestMapping("/myordersde")
	public String myordersde(int oid, Model m, String uid, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		if (cust == null) {
			return "redirect:/cust/login?prevUrl=" + prevUrl;
		} else if (cust.getId().equals(uid) == false) {
			return "redirect:/custUidError";
		} else {

			UserOrdersMyVO odinfo = null;
			List<UserOrdersMyVO> details = null;
			List<Integer> getdeid = null;

			ArrayList<UserOrdersMyVO> getodmenu = new ArrayList<UserOrdersMyVO>();
			UserOrdersMyVO menu = null;

			ArrayList<UserOrdersMyVO> getodopt = new ArrayList<UserOrdersMyVO>();
			List<UserOrdersMyVO> opt = null;

			Integer allprice = null;

			try {
				// odinfo 주문기본정보
				odinfo = ordersbiz.getodinfo(oid);
				m.addAttribute("i", odinfo);
				// odde 주문디테일
				details = ordersbiz.getodde(oid);
				m.addAttribute("d", details);

				allprice = ordersbiz.getallprice(oid);
				m.addAttribute("ap", allprice);

				// oddeid 주문디테일id
				getdeid = ordersbiz.getoddeid(oid);

				for (Integer i : getdeid) {
					// 주문메뉴리스트
					menu = ordersbiz.getoddemenu(i);
					getodmenu.add(menu);

					opt = ordersbiz.getoddeopt(i);
					getodopt.addAll(opt);

				}

				m.addAttribute("m", getodmenu);
				m.addAttribute("opt", getodopt);

			} catch (Exception e) {
			}
			m.addAttribute("center", "/user/cust/myordersde");
		}
		return "user/index";
	}

}