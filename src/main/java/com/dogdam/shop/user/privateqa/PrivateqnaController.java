package com.dogdam.shop.user.privateqa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogdam.shop.user.member.MemberDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
@RequestMapping("/user/privateqa")
public class PrivateqnaController {
	
	@Autowired
	PrivateqnaService privateqnaService; 
	
	
	/*
	 * PRIVATE Q&A LIST
	 */
	
	@GetMapping("/privateqnaList")
	public String privateqnaList(HttpSession session, Model model) {
		log.info("privateqnaList()");
		
        String nextPage = "/user/privateqa/privateqna_list";
        
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
		List<PrivateqnaDto> privateqnaDtos = privateqnaService.privateqnaList(loginedMemberDto.getU_id());
		       
        model.addAttribute("privateqnaDtos", privateqnaDtos);
		
		return nextPage;
	}
	
	@GetMapping("/deletePrivateqnaConfirm")
	public String deletePrivateqnaConfirm(@RequestParam("ws_no") int ws_no, HttpSession session) {
		log.info("deletePrivateqnaConfirm()");
		
		String nextPage = "redirect:/user/privateqa/privateqnaList";
		
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
		int result = privateqnaService.deletePrivateqnaConfirm(ws_no);
		
		if(result <= 0)
			nextPage = "/user/privateqa/delete_privateqna_ng";
			
		return nextPage;
	}
	
	@GetMapping("/createPrivateqnaForm")
	public String createPrivateqnaForm(HttpSession session) {
		log.info("createPrivateqnaForm()");
		
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
        String nextPage = "/user/privateqa/create_privateqna_form";
		
		return nextPage;
	}
	
	@PostMapping("/createPrivateqnaConfirm")
	public String createPrivateqnaConfirm(PrivateqnaDto privateqnaDto, HttpSession session) {
		log.info("createPrivateqnaConfirm()");
		
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "/user/member/login_form";
		}
		
		privateqnaDto.setU_id(loginedMemberDto.getU_id());
		
        String nextPage = "redirect:/user/privateqa/privateqnaList";
            	
     	int result = privateqnaService.createPrivateqnaConfirm(privateqnaDto);
     			
     	if (result <= 0)
     	nextPage = "user/privateqa/create_privateqna_ng";
     			     			
		return nextPage;
	}
								
}
