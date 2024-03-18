package com.dogdam.shop.user.qa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class QnaService {
	
	@Autowired
	IQnaDao qnaDao;
	
	final static public int CREATE_QNA_SUCCESS = 1;		// 도서 등록 성공
	final static public int CREATE_QNA_FAIL = -1;		// 도서 등록 실패
	
	public int createQnaConfirm(
			@Param("g_no") int g_no, 
			String u_id, QnaDto qnaDto) {
		log.info("createQnaConfirm()");
		
		Map<Object, Object> createQnaMap = new HashMap<>();
		createQnaMap.put("g_no", g_no);
		createQnaMap.put("u_id", u_id);
		createQnaMap.put("qnaDto", qnaDto);
		
	    int result = qnaDao.insertQna(createQnaMap);
	    
	    if (result > 0)
	    	return CREATE_QNA_SUCCESS;
	    else
	    	return CREATE_QNA_FAIL;
	}

	public List<QnaDto> qnaListforGoods(int g_no) {
		
		return qnaDao.listupQnaforGoods(g_no);
	}
	
    public List<QnaDto> qnaListforUser(String u_id) {


	
		return qnaDao.listupQnaforUser(u_id);
	}

	public int deleteQnaConfirm(int gqa_no) {
		log.info("deleteQnaConfirm()");
		
		return qnaDao.deleteQna(gqa_no);
	}

}