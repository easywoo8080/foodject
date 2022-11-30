package com.foodject.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HostOrdersVO {
	private int id;
	private String uid;
	private int sid;
	private String addr;
	private String addrd;
	private String phon;
	private String nick;
	private Date date;
	private int status;
	private String ask;
	private int detail_id;
	private int detail_odid;
	private int detail_mnid;
	private String detail_mname;
	private int detail_num;
	private int detail_price;
	private int dopt_did;
	private int dopt_opid;
	private String dopt_oname;
	private int dopt_oprice;
	private int allprice;
	private int shop_id;
	private String shop_name;
	private String shop_mid;
	
	
	public HostOrdersVO(String shop_mid) {
		this.shop_mid = shop_mid;
	}

	public HostOrdersVO(Date date, int allprice) {
		this.date = date;
		this.allprice = allprice;
	}

	public HostOrdersVO( String shop_mid, int allprice) {
		this.shop_mid = shop_mid;
		this.allprice = allprice;
	}

	public HostOrdersVO(int id) {
		this.id = id;
	}

	public HostOrdersVO(int id, int status) {
		this.id = id;
		this.status = status;
	}

	public HostOrdersVO(int shop_id, String shop_mid) {
		this.shop_id = shop_id;
		this.shop_mid = shop_mid;
	}


	// *.xml/selectorders
	public HostOrdersVO(int id, String uid, int sid, String addr, String addrd, String phon, String nick, Date date,
			int status, String ask, int shop_id,  String shop_mid, String shop_name) {
		this.id = id;
		this.uid = uid;
		this.sid = sid;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.nick = nick;
		this.date = date;
		this.status = status;
		this.ask = ask;
		this.shop_id = shop_id;
		this.shop_mid = shop_mid;
		this.shop_name = shop_name;
		
	}

	public HostOrdersVO(int id, String uid, int sid, String addr, String addrd, String phon, String nick, Date date,
			int status, String ask, int detail_id, int detail_odid, int detail_mnid, String detail_mname,
			int detail_num, int detail_price, int dopt_did, int dopt_opid, String dopt_oname, int dopt_oprice,
			int allprice) {
		this.id = id;
		this.uid = uid;
		this.sid = sid;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.nick = nick;
		this.date = date;
		this.status = status;
		this.ask = ask;
		this.detail_id = detail_id;
		this.detail_odid = detail_odid;
		this.detail_mnid = detail_mnid;
		this.detail_mname = detail_mname;
		this.detail_num = detail_num;
		this.detail_price = detail_price;
		this.dopt_did = dopt_did;
		this.dopt_opid = dopt_opid;
		this.dopt_oname = dopt_oname;
		this.dopt_oprice = dopt_oprice;
		this.allprice = allprice;
	}



	

	


	
	


}


