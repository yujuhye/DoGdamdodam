package com.dogdam.shop.admin.qa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogdam.shop.admin.member.AdminMemberDto;
import com.dogdam.shop.user.member.MemberDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/admin/qa")
public class AQnaController {
	
	@Autowired
	AQnaService aQnaService;
	/*
	 * GOODS Q&A LIST
	 */
	
	@GetMapping("/adminQnaList")
	public String adminQnaList(HttpSession session, Model model) {
		log.info("adminQnaList()");
		
        String nextPage = "admin/qa/admin_qna_list";
        
		/* 어드민으로 수정해야 함 */
        
		AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
		if(adminMemberDto == null)
			return "redirect:/admin/member/login_form";
		
		List<AQnaDto> aQnaDtos = aQnaService.adminQnaList();
		       
        model.addAttribute("aQnaDtos", aQnaDtos);
		
		return nextPage;
	}
	
	@PostMapping("/answerQnaConfirm")
	@ResponseBody
	public Object answerQnaConfirm(@RequestBody Map<String, String> msgMap, HttpSession session) {
		log.info("answerQnaConfirm()");
		
		AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
	    String a_id = adminMemberDto.getA_id();

		String gqaNo = msgMap.get("gqa_no");
		String gqaAnswer = msgMap.get("gqa_answer");
	    
	    int updateResult = aQnaService.answerQnaConfirm(gqaNo, gqaAnswer, a_id);
	    
	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("updateResult", updateResult);
	    
	    return resultMap;
	}
	


}
