package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.CateVO;

@Repository
@Mapper
public interface CateMapper {

	public void insert(CateVO obj) throws Exception;
	public void update(CateVO obj) throws Exception;
	public CateVO select(int id) throws Exception;
	public List<CateVO> selectall() throws Exception;
	
}
