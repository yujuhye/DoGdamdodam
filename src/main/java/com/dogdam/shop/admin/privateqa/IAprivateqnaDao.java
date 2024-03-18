package com.dogdam.shop.admin.privateqa;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAprivateqnaDao {
	
	public List<APrivateqnaDto> listupAdminPrivateqna();

	public int answerPrivateqnaYes(Map<String, Object> answerPrivateQnaMap);
		
}
