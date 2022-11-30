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
public class SearchVO {
	private int id;
	private double latt;
	private double logt;
	private String keyword;
	private int cid;
	
	
	public SearchVO(double latt, double logt, String keyword) {
		this.latt = latt;
		this.logt = logt;
		this.keyword = keyword;
	}
	
	
	

	
	
}