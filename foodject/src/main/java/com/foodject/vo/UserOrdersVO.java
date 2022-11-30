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
public class UserOrdersVO {

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
	
	
	
	
	public UserOrdersVO(int id, String uid, int sid, String addr, String addrd, String phon, String nick, Date date,
			String ask) {
		this.id = id;
		this.uid = uid;
		this.sid = sid;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.nick = nick;
		this.date = date;
		this.ask = ask;
	}
	public UserOrdersVO(int id, int status) {
		this.id = id;
		this.status = status;
	}
	public UserOrdersVO(int id, String uid, int sid, String addr, String addrd, String phon, String nick, String ask) {
		this.id = id;
		this.uid = uid;
		this.sid = sid;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.nick = nick;
		this.ask = ask;
	}
	
	// For insert(7)
	public UserOrdersVO(String uid, int sid, String addr, String addrd, String phon, String nick, String ask) {
		this.uid = uid;
		this.sid = sid;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.nick = nick;
		this.ask = ask;
	}

	
	
}
