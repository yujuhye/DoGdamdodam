package com.dogdam.shop.admin.qa;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dogdam.shop.user.qa.QnaDto;

@Mapper
public interface IAQnaDao {
	
	public List<AQnaDto> listupAdminQna();

	public int answerQnaYes(Map<Object, Object> answerQnaMap);

	public List<QnaDto> listupAQnaforGoods(int g_no);
		
}
