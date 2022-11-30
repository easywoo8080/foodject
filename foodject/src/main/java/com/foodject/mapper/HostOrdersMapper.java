package com.foodject.mapper;

import java.util.List;

import com.foodject.vo.HostOrdersVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface HostOrdersMapper {

	public String insert(HostOrdersVO obj) throws Exception;
	public void delete(int id) throws Exception;
	public void update(HostOrdersVO obj) throws Exception;
	public HostOrdersVO select(int id) throws Exception;
	public List<HostOrdersVO> selectall() throws Exception;
	public List<HostOrdersVO> selectbills(int id) throws Exception;
	public List<HostOrdersVO> selectbill(int id) throws Exception;
	public List<HostOrdersVO> selectorders(HostOrdersVO obj) throws Exception;
	public int changestatus(HostOrdersVO obj) throws Exception;
	public int mainseletcstatus(String obj) throws Exception;
	public int mainallorders(String obj) throws Exception;
	public HostOrdersVO mainallpriceday(HostOrdersVO obj) throws Exception;
	public HostOrdersVO mainallpricemonth(HostOrdersVO obj) throws Exception;
	public List<HostOrdersVO> bardayfrommonth(HostOrdersVO obj) throws Exception;
	
	
}
