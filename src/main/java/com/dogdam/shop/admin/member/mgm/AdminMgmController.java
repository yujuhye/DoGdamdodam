package com.dogdam.shop.admin.member.mgm;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogdam.shop.admin.AdminConfig;
import com.dogdam.shop.admin.PageDefaultConfig;
import com.dogdam.shop.admin.PageMakerDto;
import com.dogdam.shop.admin.member.AdminMemberDto;
import com.dogdam.shop.user.member.MemberDto;
import com.dogdam.shop.user.member.petinfo.UserPetInfoDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;



@Log4j2
@Controller
@RequestMapping(AdminConfig.BASIC_MGM_PATH)
public class AdminMgmController {

	@Autowired
	AdminConfig adminConfig;
	
	@Autowired
	AdminMgmService adminMgmService;
	
	@GetMapping(AdminConfig.LIST_AND_MGM)
	public Object listAndMgm(Model model,
							HttpSession session,
							@RequestParam(value = "pageNum", required = false, defaultValue = PageDefaultConfig.DEFAULT_PAGE_NUMBER) int pageNum, 
							@RequestParam(value = "amonut", required = false, defaultValue = PageDefaultConfig.DEFAULT_AMOUNT) int amount, 
							@RequestParam(value = "searchText", required = false, defaultValue = "") String searchText) {
		log.info("listAndMgm()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.managementViewPath(AdminConfig.LIST_AND_MGM);
		
		Map<String, Object> map;
		
		if(searchText.isEmpty()) {
			map = adminMgmService.getAllAdminItme(pageNum, amount);
		} else {
			map = adminMgmService.searchAdminItme(pageNum, amount, searchText);
		}
		
		List<AdminMemberDto> adminDtos = (List<AdminMemberDto>) map.get("adminDtos");
		PageMakerDto pageMakerDto = (PageMakerDto) map.get("pageMakerDto");
		
		model.addAttribute("adminListup", adminDtos);
		
		model.addAttribute("pageMakerDto", pageMakerDto);
		model.addAttribute("searchText", searchText);
	
		return nextPage;
		
	}
	
	
	
	@PostMapping(AdminConfig.APPROVAL_UPDATE)
	@ResponseBody
	public Object updateApproval(@RequestBody Map<String, String> apvMap ) {
		log.info("updateApproval()");
		
		try {
			return adminMgmService.updateApproval(apvMap.get("a_approval"), apvMap.get("a_no"));
			
		} catch (Exception e) {
			log.info("updateApproval() error", e);
			
			return new HashMap<String, Object>();
		}
		
		
		
	}
	
	@GetMapping(AdminConfig.WAITING_FOR_ARV_LIST)
	public String waitingForArvList(Model model, 
									@RequestParam(value = "pageNum", required = false, defaultValue = PageDefaultConfig.DEFAULT_PAGE_NUMBER) int pageNum, 
									@RequestParam(value = "amonut", required = false, defaultValue = PageDefaultConfig.DEFAULT_AMOUNT) int amount, 
									@RequestParam(value = "searchText", required = false, defaultValue = "") String searchText) {
		log.info("waitingForArvList()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.managementViewPath(AdminConfig.WAITING_FOR_ARV_LIST);
		
		Map<String, Object> map;
		
		if(searchText.isEmpty()) {
			map = adminMgmService.getWaitingAdminItme(pageNum, amount);
		} else {
			map = adminMgmService.searchWaitingAdminItme(pageNum, amount, searchText);
		}
		
		List<AdminMemberDto> adminDtos = (List<AdminMemberDto>) map.get("adminDtos");
		PageMakerDto pageMakerDto = (PageMakerDto) map.get("pageMakerDto");
		
		model.addAttribute("adminNoArvList", adminDtos);
		model.addAttribute("pageMakerDto", pageMakerDto);
		model.addAttribute("searchText", searchText);
		
		return nextPage;
	}
	
	@GetMapping(AdminConfig.APPROVED_LIST)
	public String approvedList(Model model, 
								@RequestParam(value = "pageNum", required = false, defaultValue = PageDefaultConfig.DEFAULT_PAGE_NUMBER) int pageNum, 
								@RequestParam(value = "amonut", required = false, defaultValue = PageDefaultConfig.DEFAULT_AMOUNT) int amount, 
								@RequestParam(value = "searchText", required = false, defaultValue = "") String searchText) {
		log.info("approvedList()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.managementViewPath(AdminConfig.APPROVED_LIST);
		
		Map<String, Object> map;
		
		if(searchText.isEmpty()) {
			map = adminMgmService.getApprovedAdminItme(pageNum, amount);
		} else {
			map = adminMgmService.searchApprovedAdminItme(pageNum, amount, searchText);
		}
		
		List<AdminMemberDto> adminDtos = (List<AdminMemberDto>) map.get("adminDtos");
		PageMakerDto pageMakerDto = (PageMakerDto) map.get("pageMakerDto");
		
		model.addAttribute("approvedList", adminDtos);
		model.addAttribute("pageMakerDto", pageMakerDto);
		model.addAttribute("searchText", searchText);
		
		return nextPage;
	}
	
	@GetMapping(AdminConfig.TOOLS)
	public String mgmTools(Model model) {
		log.info("mgmTools()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.managementViewPath(AdminConfig.TOOLS);
		
		return nextPage;
	}
	
	@GetMapping(AdminConfig.USER_LIST_MGM)
	public Object userListMgm(Model model, 
							@RequestParam(value = "pageNum", required = false, defaultValue = PageDefaultConfig.DEFAULT_PAGE_NUMBER) int pageNum, 
							@RequestParam(value = "amonut", required = false, defaultValue = PageDefaultConfig.DEFAULT_AMOUNT) int amount, 
							@RequestParam(value = "searchText", required = false, defaultValue = "") String searchText) {
		log.info("userListMgm()");
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.managementViewPath(AdminConfig.USER_LIST_MGM);
		
		Map<String, Object> map;
		
		if(searchText.isEmpty()) {
			map = adminMgmService.getAllUserItme(pageNum, amount);
		} else {
			map = adminMgmService.searchUserItme(pageNum, amount, searchText);
		}
		
		List<MemberDto> memberDtos = (List<MemberDto>) map.get("memberDtos");
		PageMakerDto pageMakerDto = (PageMakerDto) map.get("pageMakerDto");
		
		model.addAttribute("allUserDtos", memberDtos);
		model.addAttribute("pageMakerDto", pageMakerDto);
		model.addAttribute("searchText", searchText);
	
		return nextPage;
	}
	
	@GetMapping(AdminConfig.SELECT_USER_INFO)
	public Object selectUserInfo(Model model, 
								@RequestParam(value = "uNo") String uNo) {
		log.info("selectUserInfo()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		int u_no = Integer.parseInt(uNo);
		
		MemberDto memberDto = adminMgmService.selectUserDto(u_no);
		
		String u_id = memberDto.getU_id();
		
		UserPetInfoDto userPetInfoDto = adminMgmService.selectUserPetInfo(u_id);
		
		if(userPetInfoDto == null) {
			model.addAttribute("userPetInfoDto", null);
			
		} else {
			model.addAttribute("userPetInfoDto", userPetInfoDto);			
		}
		
		model.addAttribute("selectUserDto", memberDto);
		String nextPage = AdminConfig.managementViewPath(AdminConfig.SELECT_USER_INFO);
		
		return nextPage;
		
	}
	

	
	
	
}
