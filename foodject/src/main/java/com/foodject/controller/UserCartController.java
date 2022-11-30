package com.foodject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.biz.HostOrdersBiz;
import com.foodject.biz.UserCartBiz;
import com.foodject.biz.UserDetailBiz;
import com.foodject.biz.UserDoptBiz;
import com.foodject.biz.UserMenuBiz;
import com.foodject.biz.UserOptcartBiz;
import com.foodject.biz.UserOrdersBiz;
import com.foodject.biz.UserShopBiz;
import com.foodject.vo.HostOrdersVO;
import com.foodject.vo.UserCartVO;
import com.foodject.vo.UserCustVO;
import com.foodject.vo.UserDoptVO;
import com.foodject.vo.UserOptcartVO;
import com.foodject.vo.UserOrdersVO;
import com.foodject.vo.UserShopVO;

@Controller
@RequestMapping("/cart")
public class UserCartController {
	
	@Autowired
	UserCartBiz crbiz;
	
	@Autowired
	UserOptcartBiz ocbiz;
	
	@Autowired
	UserShopBiz sbiz;

	@Autowired
	UserOrdersBiz obiz;
	
	@Autowired
	UserDetailBiz debiz;
	
	@Autowired
	UserDoptBiz dbiz;
	
	@Autowired
	UserMenuBiz mnbiz;

	@Autowired
	HostOrdersBiz hobiz;

	@RequestMapping("paytest")
	public String paytest(Model m) {
		m.addAttribute("center", "user/cart/center2" );
		return "user/index";
	}		

	

	@RequestMapping("")
	public String main(Model m, String uid, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");

		if(cust == null) {
			return "redirect:/cust/login?prevUrl="+prevUrl;
		}else if(cust.getId().equals(uid) == false) {
			return "redirect:/custUidError";
		}else{	
			int count = 0;
			int sid = 0;
			String id = cust.getId();
			String nickname = cust.getNick();
			UserShopVO shop = null;
			List<UserCartVO> crlist = null;
			List<UserOptcartVO> oclist = null;
				
			int ctid = 0;
			int cnt = 0;
				
			try {
				
				sid = crbiz.getSid_byUid(uid);
				
				shop = sbiz.get(sid);
				m.addAttribute("shop",shop);
				System.out.println(shop);
				
				UserCartVO obj = new UserCartVO(0, uid,sid);
				crlist = crbiz.get_byUid(obj);
				m.addAttribute("crlist",crlist);
					
				oclist = ocbiz.get_byUid(uid);
				
	
				for (UserCartVO cr : crlist) {
					ctid = cr.getId();
//					System.out.println(ctid);
					//oclistForSize = ocbiz.get_byCtid(cr.getId());
//					for (UserOptcartVO oc : oclistForSize) {
//						System.out.println(oc);
//					}
					cnt = ocbiz.getCount(ctid);
					cr.setCount(cnt);
				}	
				m.addAttribute("oclist", oclist);

				
			} catch (Exception e) {
				m.addAttribute("count",count);
				System.out.println("cart is empty");
			}	
				
			m.addAttribute("user",id+"( "+nickname+" ) ");
			m.addAttribute("center", "user/cart/center" );


		}		
		return "user/index";
	}			
	
	@RequestMapping("/delete")
	public String deleteCart(Model m, String uid, int cartId, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		if(cust == null) {
			return "redirect:/cust/login";
		}else if(cust.getId().equals(uid) == false) {
			
			return "redirect:/pathError";
		}else{
			//System.out.println("delete");
			try {
				crbiz.remove(cartId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:"+prevUrl;
		}
	}
	
	@RequestMapping("order")
	public String order(Model m, String uid, HttpSession session, int totalPrice) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		
		if(cust == null) {
			return "redirect:/cust/login";
		}else if(cust.getId().equals(uid) == false || totalPrice == 0 ) {
			return "redirect:/pathError";
		}else{
			int sid = 0;
			UserShopVO shop = null;
			List<UserCartVO> crlist = null;
			
			try {
				sid = crbiz.getSid_byUid(uid);
				shop = sbiz.get(sid);
				m.addAttribute("shop",shop);
				
				UserCartVO obj = new UserCartVO(0, uid,sid);
				crlist = crbiz.get_byUid(obj);
				
				m.addAttribute("mname",crlist.get(0).getMname());
				m.addAttribute("cnt",crlist.size()-1);
				m.addAttribute("cust",cust);
				m.addAttribute("uid",uid);
				m.addAttribute("totalPrice",totalPrice);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.addAttribute("center","user/cart/order");
			return "user/index";
		}
	}
	
	@RequestMapping("orderimple")
	public String orderimple(Model m, String uid, HttpSession session, UserOrdersVO order) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		
		if(cust == null) {
			return "redirect:/cust/login";
		}else if(cust.getId().equals(uid) == false) {
			return "redirect:/pathError";
		}else{
			int sid = 0;
			int cartId = 0;
			List<UserCartVO> crlist = null;
			List<UserOptcartVO> oclist = null;
			//UserOrdersVO order = null;
			
			
//			/* Orders */
//			String addr = "주소";
//			String addrd = "상세주소";
//			String phon = "010-0000-00000";
//			String nick = cust.getNick();
//			String ask = "많이 주세요.";
			System.out.println(order);
			order.setUid(uid);
			
			/* detail */
			int odid = 0;
			int mnid = 0;
			String mname = "";
			int num = 0;
			int price = 0;
			
			/* dopt */
			int did = 0;
			int opid = 0;
			String oname = "";
			int oprice = 0;
					
			
			try {
				sid = crbiz.getSid_byUid(uid);
				System.out.println(sid);
				order.setSid(sid);
				UserCartVO obj = new UserCartVO(0, uid,sid);
				crlist = crbiz.get_byUid(obj);
				
				
				// order = new UserOrdersVO(uid,sid,addr,addrd,phon,nick,ask);
				obiz.register(order);
				odid = order.getId();
				System.out.println("odid : " + odid);
				

				for (UserCartVO cart : crlist) {
					// System.out.println(cart);
					
					cartId = cart.getId();
					cart.setOdid(odid);
					// detail에 추가
					debiz.register(cart);
					did = cart.getId();
					// System.out.println("did : "+did);
					
					// optcart 가져오기
					oclist = ocbiz.get_byCtid(cartId);
					for (UserOptcartVO optcart : oclist) {
						System.out.println(optcart);
						opid = optcart.getOid();
						oname = optcart.getName();
						oprice = optcart.getPrice();
						// dopt에 추가
						dbiz.register(new UserDoptVO(did,opid,oname,oprice));
					}
					// cart에서 항목 지우기
					crbiz.remove(cartId);
				}
				



			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:/orderError";
			}
			
			HostOrdersVO hovo = null;
			// 웹소켓 데이터
			try {
				
				hovo = hobiz.get(odid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("UserCartController : hovo :: " + hovo);



			m.addAttribute("soket_data",hovo);
			return "/user/cart/orderComple";
		}
	}
				
//	@RequestMapping("/quantity_modify")
//	public String quantity_modify(Model m, HttpSession session) {
//		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
//		String uid = cust.getId();
//				
//		return "redirect:/cart";
//	}
}