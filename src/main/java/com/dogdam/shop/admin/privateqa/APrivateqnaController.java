package com.dogdam.shop.admin.privateqa;

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
@RequestMapping("/admin/privateqa")
public class APrivateqnaController {
	
	@Autowired
	APrivateqnaService aPrivateqnaService;
	/*
	 * PRIVATE Q&A LIST
	 */
	
	@GetMapping("/adminPrivateqnaList")
	public String adminPrivateqnaList(HttpSession session, Model model) {
		log.info("adminPrivateqnaList()");
		
        String nextPage = "/admin/privateqa/admin_privateqna_list";
        
        AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
		if(adminMemberDto == null)
			return "redirect:/admin/member/login_form";
		
		List<APrivateqnaDto> aPrivateqnaDtos = aPrivateqnaService.adminPrivateqnaList();
		       
        model.addAttribute("aPrivateqnaDtos", aPrivateqnaDtos);
		
		return nextPage;
	}
	
	@PostMapping("/answerPrivateqnaConfirm")
	@ResponseBody
	public Object answerPrivateqnaConfirm(@RequestBody Map<String, String> msgMap, HttpSession session) {
	    log.info("answerPrivateqnaConfirm()");

	    AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
	    String a_id = adminMemberDto.getA_id();

	    String wsNo = msgMap.get("ws_no");
	    String wsAnswer = msgMap.get("ws_answer");

	    int updateResult = aPrivateqnaService.answerPrivateqnaConfirm(wsNo, wsAnswer, a_id);

	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("updateResult", updateResult);

	    return resultMap;
	}
	


}
