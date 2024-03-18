package com.dogdam.shop.admin.privateqa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class APrivateqnaService {
	
	@Autowired
	IAprivateqnaDao aPrivateqnaDao;
	
    public List<APrivateqnaDto> adminPrivateqnaList() {
	log.info("adminPrivateqnaList()");

	    List<APrivateqnaDto> aPrivateqnaDtos = aPrivateqnaDao.listupAdminPrivateqna();
		return aPrivateqnaDtos;
	}


    public int answerPrivateqnaConfirm(String ws_no, String ws_answer, String a_id) {
        log.info("answerPrivateqnaConfirm()");
        
		/* ?? */
        APrivateqnaDto aPrivateqnaDto = new APrivateqnaDto();
        aPrivateqnaDto.setA_id(a_id);
        
        int wsNoInteger = Integer.parseInt(ws_no);
        
        Map<String, Object> answerPrivateQnaMap = new HashMap<>();
        answerPrivateQnaMap.put("ws_no", wsNoInteger);
        answerPrivateQnaMap.put("ws_answer", ws_answer);
        answerPrivateQnaMap.put("a_id", a_id);		      
        
        return aPrivateqnaDao.answerPrivateqnaYes(answerPrivateQnaMap);
    }

}