package com.dogdam.shop.admin.qa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogdam.shop.admin.privateqa.APrivateqnaDto;
import com.dogdam.shop.user.qa.QnaDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AQnaService {
	
	@Autowired
	IAQnaDao aQnaDao;
	
    public List<AQnaDto> adminQnaList() {
	log.info("adminQnaList()");

	    List<AQnaDto> aQnaDtos = aQnaDao.listupAdminQna();
		return aQnaDtos;
	}


    public int answerQnaConfirm(@Param("gqa_no") String gqa_no, @Param("gqa_answer") String gqa_answer, String a_id) {
	log.info("answerQnaConfirm()");
	
	AQnaDto aQnaDto = new AQnaDto();
    aQnaDto.setA_id(a_id);
	
    int wsNoInteger = Integer.parseInt(gqa_no);
    
    Map<Object, Object> answerQnaMap = new HashMap<>();
    
    answerQnaMap.put("gqa_no", wsNoInteger);
    answerQnaMap.put("gqa_answer", gqa_answer);
    answerQnaMap.put("a_id", a_id);
    
    
    
	return aQnaDao.answerQnaYes(answerQnaMap);
	
}

	public List<QnaDto> aQnaListforGoods(int g_no) {
		return aQnaDao.listupAQnaforGoods(g_no);
	}

}