package com.foodject.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserCartVO {
	private int id;
	private String uid;
	private int mnid;
	private int num;
	private int sid;
	
	private String mname;
	private int total;
	private int count;
	private int deprice;
	private int odid;
	private int mprice;
	
	private String img;
	
	// For insert
	public UserCartVO(String uid, int mnid, int num) {
		this.uid = uid;
		this.mnid = mnid;
		this.num = num;
	}
	
	// For updateNum
	public UserCartVO(int id, int num) {
		this.id = id;
		this.num = num;
	}
	
	// For updateCount
	public UserCartVO(int id, int count, int c) {
		this.id = id;
		this.count = count;
	}
	
	// For selectForInsert
	public UserCartVO(String uid, int mnid) {
		this.uid = uid;
		this.mnid = mnid;
	}

	public UserCartVO(int id,String uid, int sid) {
		this.uid = uid;
		this.sid = sid;
	}
	
	

	
	
	
	
}