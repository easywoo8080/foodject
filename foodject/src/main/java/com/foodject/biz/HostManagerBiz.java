package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.HostManagerMapper;
import com.foodject.vo.HostManagerVO;
@Service
public class HostManagerBiz implements Biz<String, HostManagerVO> {

	@Autowired
	HostManagerMapper dao;
	
	@Override
	public void register(HostManagerVO v) throws Exception {
		dao.insert(v);
		
	}

	@Override
	public void modify(HostManagerVO v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HostManagerVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<HostManagerVO> get() throws Exception {
		return dao.selectall();
	}
	public void modifysts(HostManagerVO v) throws Exception {
		dao.updatests(v);
	}
	public void findpwd(HostManagerVO v) throws Exception {
		dao.selectpwd(v);
	}
	public void modifypwd(HostManagerVO v) throws Exception {
		dao.updatepwd(v);
	}
}
