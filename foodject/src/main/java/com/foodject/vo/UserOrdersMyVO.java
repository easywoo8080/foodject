package com.foodject.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserOrdersMyVO {
	
	//orders
	private int oid;
	private String uid;
	private String addr;
	private String addrd;
	private String phon;
	private String nick;
	private Date odate;	
	private int status;
	private String ask;
	private int allprice;
	
	//orders detail
	private int deid;
	private int mid;
	private String mname;	
	private int mnum;	
	private int mprice;	
	
	//orders option detail
	private int opid;
	private String opname;
	private int opprice;
	
	//shop
	private String sid;
	private String sname;
	private String slogo;

	//delete
	public UserOrdersMyVO(int oid, int status, int c) {
		this.oid = oid;
		this.status = status;
	}

	//selectmy
	public UserOrdersMyVO(String uid, Date odate, String sname, int oid, String slogo) {
		this.uid = uid;
		this.odate = odate;
		this.sname = sname;
		this.slogo = slogo;
		this.oid = oid;
	}

	public UserOrdersMyVO(int oid, int allprice) {
		this.oid = oid;
		this.allprice = allprice;
	}

	public UserOrdersMyVO(int oid, String uid) {
		super();
		this.oid = oid;
		this.uid = uid;
	}
	
	

	
	
}
