package com.foodject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.UserCartVO;

@Repository
@Mapper
public interface UserDetailMapper {
	public void insert(UserCartVO obj) throws Exception;
}
