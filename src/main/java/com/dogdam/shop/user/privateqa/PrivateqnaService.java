package com.dogdam.shop.user.privateqa;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PrivateqnaService {
	
	@Autowired
	IPrivateqnaDao privateqnaDao;
	
	final static public int CREATE_PRIVATEQNA_SUCCESS = 1;		// 도서 등록 성공
	final static public int CREATE_PRIVATEQNA_FAIL = -1;		// 도서 등록 실패
	
	public int createPrivateqnaConfirm(PrivateqnaDto privateqnaDto) {
		log.info("createPrivateqnaConfirm()");
		
		
	    int result = privateqnaDao.insertPrivateQna(privateqnaDto);
	    
	    if (result > 0)
	    	return CREATE_PRIVATEQNA_SUCCESS;
	    else
	    	return CREATE_PRIVATEQNA_FAIL;
	}

	public List<PrivateqnaDto> privateqnaList(@Param("u_id") String u_id) {
		
		return privateqnaDao.listupPrivateqna(u_id);
	}

	public int deletePrivateqnaConfirm(@Param("ws_no") int ws_no) {
		log.info("deletePrivateqnaConfirm()");
		
		return privateqnaDao.deletePrivateqna(ws_no);
	}

}