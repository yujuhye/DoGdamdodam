package com.dogdam.shop.user.qa;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IQnaDao {
    
    public int insertQna(Map<Object, Object> createQnaMap); 

	public List<QnaDto> listupQnaforGoods(@Param("g_no") int g_no); 
	
	public List<QnaDto> listupQnaforUser(@Param("u_id") String u_id); 

	public int deleteQna(int gqa_no);
	
}