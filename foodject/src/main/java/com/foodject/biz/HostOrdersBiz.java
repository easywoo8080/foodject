package com.foodject.biz;

import java.util.List;

import com.foodject.frame.Biz;
import com.foodject.mapper.HostOrdersMapper;
import com.foodject.vo.HostOrdersVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HostOrdersBiz implements Biz<Integer, HostOrdersVO> {

	@Autowired
	HostOrdersMapper dao;

	
	@Override
	public void register(HostOrdersVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(HostOrdersVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public HostOrdersVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<HostOrdersVO> get() throws Exception {
		return dao.selectall();
	}
	public List<HostOrdersVO> selectbills(Integer k) throws Exception{
		return dao.selectbills(k);
	}
	public List<HostOrdersVO> selectbill(Integer k) throws Exception{
		return dao.selectbill(k);
	}
	public List<HostOrdersVO> selectorders(HostOrdersVO obj) throws Exception{
		return dao.selectorders(obj);
	}
	public int changestatus(HostOrdersVO obj) throws Exception{
		return dao.changestatus(obj);
	}

	public int mainseletcstatus(String obj) throws Exception{
		return dao.mainseletcstatus(obj);
	}
	public int mainallorders(String obj) throws Exception{
		return dao.mainallorders(obj);
	}
	public HostOrdersVO mainallpriceday(HostOrdersVO obj) throws Exception{
		return dao.mainallpriceday(obj);
	}
	public HostOrdersVO mainallpricemonth(HostOrdersVO obj) throws Exception{
		return dao.mainallpricemonth(obj);
	}
	public List<HostOrdersVO> bardayfrommonth(HostOrdersVO obj) throws Exception{
		return dao.bardayfrommonth(obj);
	}
}
