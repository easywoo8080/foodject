package com.foodject.vo;

import java.sql.Date;

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
public class HostManagerVO {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date date;
	private Date birth;
	private String phon;
	private int status;
	public HostManagerVO(String id, String pwd, String name, String email, Date date, Date birth, String phon) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.date = date;
		this.birth = birth;
		this.phon = phon;
	}
	public HostManagerVO(String id, String pwd, String name, String email, Date birth, String phon) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.phon = phon;
	}
	public HostManagerVO(String id, int status) {
		super();
		this.id = id;
		this.status = status;
	}
	public HostManagerVO(String id, String pwd, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
	}
	public HostManagerVO(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	
	
	
	
}
