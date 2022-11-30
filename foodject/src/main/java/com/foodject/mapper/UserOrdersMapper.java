package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.UserOrdersMyVO;
import com.foodject.vo.UserOrdersVO;

@Repository
@Mapper
public interface UserOrdersMapper {
	public void insert(UserOrdersVO obj) throws Exception;
	
	public List<UserOrdersMyVO> selectod(String uid) throws Exception;
	public List<UserOrdersMyVO> selectodde(int oid) throws Exception;
	public UserOrdersMyVO selectodinfo(Integer id) throws Exception;
	public List<Integer> selectoddeid(int oid) throws Exception;
	public UserOrdersMyVO selectoddemenu(int deid) throws Exception;
	public List<UserOrdersMyVO> selectoddeopt(int deid) throws Exception;
	public Integer selectallprice(int oid) throws Exception;
	public Integer selectcount(String uid) throws Exception;

}
