package com.foodject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.UserDoptVO;

@Repository
@Mapper
public interface UserDoptMapper {
	public void insert(UserDoptVO obj) throws Exception;
	
}
