package com.dogdam.shop.user.privateqa;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IPrivateqnaDao {
    
    public int insertPrivateQna(PrivateqnaDto privateqnaDto); 

	public List<PrivateqnaDto> listupPrivateqna(@Param("u_id") String u_id); 

	public int deletePrivateqna(@Param("ws_no") int ws_no);
	
}