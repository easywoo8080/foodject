package com.foodject.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.foodject.biz.HostOrdersBiz;
import com.foodject.biz.HostShopBiz;
import com.foodject.frame.Util;
import com.foodject.restapi.NaverORC;
import com.foodject.vo.HostManagerVO;
import com.foodject.vo.HostOrdersVO;
import com.foodject.vo.HostShopVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/host/shop")
public class HostShopAJAX {

	@Autowired
	HostShopBiz sbiz;
	@Autowired
	NaverORC orc;
	@Autowired
	Util ut;
	@Autowired
	HostOrdersBiz obiz;
	@Value("${userDir}")
	String userDir;

	
	@RequestMapping("barChart")
	public Object barChart( int id  ,HttpSession session) {
		HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
		List<HostOrdersVO> list = null;
		HostOrdersVO ovo = new HostOrdersVO();
		

		if( id == 0 ){
			ovo.setShop_mid(manager.getId());
		}
		
		try {
			list = obiz.bardayfrommonth(ovo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return list;
	}
	@RequestMapping("bill")
	public Object bill( int id  ) {
		List<HostOrdersVO> list = null;
		try {
			list = obiz.selectbill(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping("shopdelete")
	public void shopdelete( int[] sArray  ) {
		// HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
	

		for (int i = 0; i < sArray.length; i++) {
		
		}
			try {
				
				for (int i = 0; i < sArray.length; i++) {
					sbiz.remove(sArray[i]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	@RequestMapping("shopModify")
	public void shopModify( MultipartHttpServletRequest  mfsr,  HostShopVO obj  ) {
		// HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
		System.out.println("shopModify start : ");
		MultipartFile mf = null;
		String filename = "";
		Iterator<String> it = mfsr.getFileNames();
		while( it.hasNext() ){
		filename = it.next();
		mf = mfsr.getFile(filename);
		}
		if( mf == null ){
			System.out.println("mf null ");
		}else{
			ut.saveFile(mf, obj.getLogo(), "shop");

		}
		System.out.println("mf : "+mf);
		System.out.println("filename : "+filename);
		System.out.println("HostShopVO : " + obj);
		System.out.println("shopModify name end ");
		
		try {
			sbiz.modify(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end obj; " + obj);
	}
	@RequestMapping("shopinfo")
	public Object shopinfo(int  id) {
		// HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
		System.out.println("shopinfo id : " + id);
		HostShopVO obj = null;
		try {
			obj = sbiz.get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	@RequestMapping("checkbnum")
	public Object checkbnum(MultipartHttpServletRequest  mfsr, HttpSession session) {
		// HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
		Object obj = null;
		if( mfsr == null ){
			System.out.println(mfsr);

			return "0";
		}
		MultipartFile bnumcheck = null;
		String filename = "";
		Iterator<String> it = mfsr.getFileNames();
		while( it.hasNext() ){
			filename = it.next();
			bnumcheck = mfsr.getFile(filename);
		}
		byte [] data;

		String filePath = "";
		String savename = bnumcheck.getOriginalFilename();
		
		String[] splitname = savename.split("[.]");


		System.out.println(splitname.length);
		savename = "bnumimg" +  "." + splitname[splitname.length - 1];
		filePath = userDir + File.separator+  savename;
        

		
		// System.out.println("Os name : "+System.getProperty("os.name").toLowerCase().startsWith("win"));
        System.out.println("path Result : " + filePath);

		try {
			data = bnumcheck.getBytes();
			FileOutputStream fo =
					new FileOutputStream(filePath);
			fo.write(data);
			fo.close();
			obj = orc.checkNaverORC(filePath);
			

		} catch (IOException e) {
			
		}

		return obj;
	}
	

}

