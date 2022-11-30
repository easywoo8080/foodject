package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.UserShopMapper;
import com.foodject.vo.MarkerVO;
import com.foodject.vo.SearchVO;
import com.foodject.vo.UserShopVO;

@Service
public class UserShopBiz implements Biz<Integer, UserShopVO> {

	@Autowired
	UserShopMapper dao;
	
	@Override
	public void register(UserShopVO v) throws Exception {
		dao.insert(v);	
	}

	@Override
	public void modify(UserShopVO v) throws Exception {
		dao.update(v);		
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public UserShopVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<UserShopVO> get() throws Exception {
		return dao.selectall();
	}
	
	public void modifySts(UserShopVO v) throws Exception {
		dao.updateSts(v);
	}
	
	public List<UserShopVO> getSts(int k) throws Exception {
		return dao.select_bySts(k);
	}
	
	public List<UserShopVO> getMain(MarkerVO obj) throws Exception {
		return dao.selectMain(obj);
	}
	
	public List<UserShopVO> search(SearchVO obj) throws Exception{
		return dao.search(obj);
	}

}
