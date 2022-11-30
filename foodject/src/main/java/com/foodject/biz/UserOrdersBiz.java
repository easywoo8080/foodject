package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.UserOrdersMapper;
import com.foodject.vo.UserOrdersMyVO;
import com.foodject.vo.UserOrdersVO;

@Service
public class UserOrdersBiz implements Biz<Integer, UserOrdersVO> {

	@Autowired
	UserOrdersMapper dao;

	@Override
	public void register(UserOrdersVO v) throws Exception {
		dao.insert(v);		
	}
	
	public List<UserOrdersMyVO> getod(String k) throws Exception{
		return dao.selectod(k);
	}

	public List<UserOrdersMyVO> getodde(Integer k) throws Exception{
		return dao.selectodde(k);
	}
	
	public UserOrdersMyVO getodinfo(Integer k) throws Exception{
		return dao.selectodinfo(k);
	}
	
	public List<Integer> getoddeid(Integer k) throws Exception{
		return dao.selectoddeid(k);
	}
	
	public UserOrdersMyVO getoddemenu(Integer k) throws Exception{
		return dao.selectoddemenu(k);
	}
	
	public List<UserOrdersMyVO> getoddeopt(Integer k) throws Exception{
		return dao.selectoddeopt(k);
	}
	
	public Integer getallprice(Integer k) throws Exception{
		return dao.selectallprice(k);
	}
	
	public Integer getcount(String k) throws Exception{
		return dao.selectcount(k);
	}

	

	@Override
	public void modify(UserOrdersVO v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserOrdersVO get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserOrdersVO> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
