package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.UserCartMapper;
import com.foodject.vo.UserCartVO;

@Service
public class UserCartBiz implements Biz<Integer, UserCartVO> {

	@Autowired
	UserCartMapper dao;
	
	@Override
	public void register(UserCartVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(UserCartVO v) throws Exception {
		dao.updateNum(v);		
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);		
	}

	@Override
	public UserCartVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<UserCartVO> get() throws Exception {
		return dao.selectall();
	}
	
	public List<UserCartVO> getForInsert(UserCartVO v) throws Exception{
		return dao.selectForInsert(v);
	}
	
	public List<UserCartVO> get_byUid(UserCartVO v) throws Exception {
		return dao.select_byUid(v);
	}

	public Integer getSid_byUid(String uid) throws Exception{
		return dao.selectSid_byUid(uid);
	}
	
	public void set(UserCartVO v) throws Exception{
		dao.updateCount(v);
	}

	public void modifyDeprice(UserCartVO v) throws Exception{
		dao.updateDeprice(v);
	}
	
}
