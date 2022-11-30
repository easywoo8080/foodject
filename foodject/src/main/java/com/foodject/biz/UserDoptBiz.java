package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.UserDoptMapper;
import com.foodject.vo.UserDoptVO;

@Service
public class UserDoptBiz implements Biz<Integer, UserDoptVO> {

	@Autowired
	UserDoptMapper dao;
	
	@Override
	public void register(UserDoptVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(UserDoptVO v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDoptVO get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDoptVO> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
