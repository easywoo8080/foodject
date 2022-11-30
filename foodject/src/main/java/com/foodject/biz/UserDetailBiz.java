package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.UserDetailMapper;
import com.foodject.vo.UserCartVO;

@Service
public class UserDetailBiz implements Biz<Integer, UserCartVO> {

	@Autowired
	UserDetailMapper dao;
	
	@Override
	public void register(UserCartVO v) throws Exception {
		dao.insert(v);
		
	}

	@Override
	public void modify(UserCartVO v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserCartVO get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserCartVO> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
