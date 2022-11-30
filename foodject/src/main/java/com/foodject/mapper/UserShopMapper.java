package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.MarkerVO;
import com.foodject.vo.SearchVO;
import com.foodject.vo.UserShopVO;

@Repository
@Mapper
public interface UserShopMapper {

	public void insert(UserShopVO obj) throws Exception;
	public void update(UserShopVO obj) throws Exception;
	public void delete(int id) throws Exception;
	public UserShopVO select(int id) throws Exception;
	public List<UserShopVO> selectall() throws Exception;

	public void updateSts(UserShopVO obj) throws Exception;
	public List<UserShopVO> select_bySts(int status) throws Exception;

	public List<UserShopVO> select_byCate(int cid) throws Exception;
	public List<UserShopVO> selectMain(MarkerVO obj) throws Exception;
	public List<UserShopVO> search(SearchVO obj) throws Exception;
}




