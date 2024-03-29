package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.CateMapper;
import com.foodject.vo.CateVO;

@Service
public class CateBiz implements Biz<Integer, CateVO> {
	
	@Autowired
	CateMapper dao;

	@Override
	public void register(CateVO v) throws Exception {
		dao.insert(v);
		
	}

	@Override
	public void modify(CateVO v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		
		
	}

	@Override
	public CateVO get(Integer k) throws Exception {
		
		return dao.select(k);
	}

	@Override
	public List<CateVO> get() throws Exception {
		return dao.selectall();
	}

	

}
