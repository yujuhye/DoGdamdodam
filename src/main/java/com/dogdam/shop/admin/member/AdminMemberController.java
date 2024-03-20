package com.dogdam.shop.admin.member;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogdam.shop.admin.AdminConfig;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;



@Log4j2
@Controller
@RequestMapping(AdminConfig.BASIC_PATH)
public class AdminMemberController {
	
	@Autowired
	AdminConfig adminConfig;
	
	@Autowired
	AdminMemberService adminMemberService;

	
	@GetMapping(AdminConfig.CREATE_ACCOUNT_FORM)
	public String create_account_form(Model model) {
		log.info("create_account_form()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.generateViewPath(AdminConfig.CREATE_ACCOUNT_FORM);
		
		return nextPage;
	}
	
	@PostMapping(AdminConfig.CREATE_ACCOUNT_CONFIRM)
	public String create_account_confirm(AdminMemberDto memberDto, Model model) {
		log.info("create_account_confirm()");
		
		String nextPage = AdminConfig.generateViewPath(AdminConfig.CREATE_ACCOUNT_RESULT);
		
		int result = adminMemberService.create_account_confirm(memberDto);
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		model.addAttribute(AdminConfig.SIGN_UP_RESULT, result);
		
		return nextPage;
	}
	
	@GetMapping(AdminConfig.LOGIN_FORM)
	public String login_form(Model model) {
		log.info("login_form()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.generateViewPath(AdminConfig.LOGIN_FORM);
		
		return nextPage;
	}
	
	@PostMapping(AdminConfig.LOGIN_CONFIRM)
	public String login_confirm(Model model, AdminMemberDto memberDto, HttpSession session) {
		log.info("login_confirm()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.generateViewPath(AdminConfig.LOGIN_RESULT);
		
		AdminMemberDto adminMemberDto = adminMemberService.login_confirm(memberDto);
		
		int result = AdminConfig.LOGIN_FAIL;
		
		if(adminMemberDto != null) {
			log.info("admin login success");
			
			result = AdminConfig.LOGIN_SUCCESS;
			session.setAttribute("adminMemberDto", adminMemberDto);
			session.setMaxInactiveInterval(60 * 60);
			
		} 
		
		model.addAttribute(AdminConfig.SIGN_IN_RESULT, result);
		
		return nextPage;
	}
	
	@GetMapping(AdminConfig.LOGOUT_CONFIRM)
	public String logout_confirm(HttpSession session, Model model) {
		log.info("logout_confirm()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = "redirect:/admin";
		
		session.removeAttribute("adminMemberDto");
		
		return nextPage;
	}
	
	@PostMapping(AdminConfig.ID_VALUE_TEST)
	@ResponseBody
	public Object selectIdIsMember(@RequestBody Map<String, String> idMap) {
		log.info("selectIdIsMember()");
		
		Map<String, Object> resultMap = new HashMap<>();
		
		int result = adminMemberService.selectForId(idMap.get("a_id").toString());
		
		resultMap.put("success", result > 0);
		
		return resultMap;
	}
	
	
	
	
	
	
	
}
