package com.dogdam.shop.user.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/user/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@GetMapping("/create_account_form")
	public String createAccountForm() {
		log.info("createAccountForm()");

		String nextPage = "user/member/create_account_form";

		return nextPage;

	}

	@PostMapping("/create_account_confirm")
	public String createAccountConfirm(MemberDto memberDto) {
		log.info("createAccountConfirm()");

		String nextPage = "user/member/create_account_success";

		int result = memberService.createAccountConfirm(memberDto);

		if (result <= 0) {

			nextPage = "user/member/create_account_fail";

		}

		return nextPage;
	}

	@GetMapping("/login_form")
	public String loginForm() {

		log.info("loginForm()");

		String nextPage = "user/member/login_form";

		return nextPage;
	}

	@PostMapping("/login_confirm")
	public String loginConfirm(MemberDto memberDto, HttpSession session) {
		log.info("loginConfirm()");
		String nextPage = "user/member/login_success";
		MemberDto loginedMemberDto = memberService.loginConfirm(memberDto);
		if (loginedMemberDto != null) {
			
			session.setAttribute("loginedMemberDto", loginedMemberDto);
			session.setMaxInactiveInterval(60 * 30);
			
		} else {
			nextPage = "user/member/login_fail";
			
		}
		
		return nextPage;
	}

	@GetMapping("/modify_form")
	public String modifyForm() {
		log.info("modifyForm()");

		String nextPage = "user/member/modify_form";

		return nextPage;
	}

	@PostMapping("/modify_confirm")
	public String modifyConfirm(MemberDto memberDto, HttpSession session) {
		log.info("ModifyConfirm()");
		String nextPage = "user/member/modify_success";
		MemberDto loginedMemberDto = memberService.modfiyConfirm(memberDto);
		if (loginedMemberDto != null) {
			session.setAttribute("loginedMemberDto", loginedMemberDto);
			session.setMaxInactiveInterval(60 * 60);
		} else {
			nextPage = "user/member/modify_fail";
		}
		return nextPage;
	}

	@GetMapping("/logout_confirm")
	public String logoutConfirm(HttpSession session) {
		log.info("modifyConfirm()");

		String nextPage = "redirect:/";

		session.removeAttribute("loginedMemberDto");

		return nextPage;
	}

	@GetMapping("/delete_confirm")
	public String deleteConfirm(HttpSession session) {
		log.info("deleteConfirm()");

		String nextPage = "user/member/delete_success";
		MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");

		int result = memberService.deleteConfirm(loginedMemberDto.getU_no(), loginedMemberDto.getU_id());
		if (result <= 0) {
			nextPage = "user/member/delete_fail";
		} else {
			session.removeAttribute("loginedMemberDto");			
		}
		

		return nextPage;
	}
	
	@PostMapping("/id_value_test")
	@ResponseBody
	public Object selectIdIsMember(@RequestBody Map<String, String> idMap) {
		log.info("selectIdIsMember()");
		Map<String, Object> resultMap = new HashMap<>();
		
		int result = memberService.selectForId(idMap.get("u_id").toString());
		
		resultMap.put("success", result > 0);
		
		return resultMap;
	}
	

}