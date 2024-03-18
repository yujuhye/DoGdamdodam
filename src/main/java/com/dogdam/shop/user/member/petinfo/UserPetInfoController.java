package com.dogdam.shop.user.member.petinfo;

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

import com.dogdam.shop.user.member.MemberDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/user/member/pet")
public class UserPetInfoController {
	
	@Autowired
	UserPetInfoService userPetInfoService;

	@GetMapping("/my_pet_infos")
	public Object userPetInfos(Model model, HttpSession session) {
		log.info("userPetInfos()");
		
		String nextPage = "user/member/petinfo/my_pet_infos";
		
		MemberDto memberDto = (MemberDto) session.getAttribute("loginedMemberDto");
		String u_id = memberDto.getU_id();
		
		model.addAttribute("uId", u_id);
		
		
		return nextPage;
		
	}
	
	@PostMapping("/my_pet_list_up")
	@ResponseBody
	public Object petListUp(@RequestBody Map<String, String> idMap) {
		log.info("petListUp()");
		
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> map = userPetInfoService.selectUserPetInfos(idMap.get("u_id").toString());
		
		UserPetInfoDto mainPetDto = (UserPetInfoDto) map.get("petInfoDto");
		List<UserPetInfoDto> petInfoDtos = (List<UserPetInfoDto>) map.get("userPetInfoDtos");
		if(petInfoDtos.size() == 0) {
			resultMap.put("success", false);
		} else {
			
			if(mainPetDto == null) {
			
				resultMap.put("NoMainpet", true);
				
				
			} else {
				resultMap.put("NoMainPet", false);
				resultMap.put("mainPetDto", mainPetDto);
			}
			
			resultMap.put("success", true);
			resultMap.put("petInfoDtos", petInfoDtos);
			
		}
		
		return resultMap;
	}
	
	
	@PostMapping("/registerNewPet")
	@ResponseBody
	public Object registerNewPet(@RequestBody Map<String, String> petMap, HttpSession session) {
		log.info("registerNewPet()");
		
		Map<String, Object> map = new HashMap<>();
		MemberDto memberDto = (MemberDto) session.getAttribute("loginedMemberDto");
		String u_id = memberDto.getU_id();
		String p_name = petMap.get("p_name").toString();
		String p_species = petMap.get("p_species").toString();
		int p_age = Integer.parseInt(petMap.get("p_age").toString());
		
		int result = userPetInfoService.insertNewPetInfo(u_id, p_name, p_species, p_age);
		map.put("success", result > 0);
		
		return map;
	}
	
	@PostMapping("/modify_pet")
	@ResponseBody
	public Object modifyPet(@RequestBody Map<String, String> petMap) {
		log.info("modifyPet()");
		
		Map<String, Object> map = new HashMap<>();
		
		int p_no = Integer.parseInt(petMap.get("p_no"));
		String p_name = petMap.get("p_name").toString();
		String p_species = petMap.get("p_species").toString();
		int p_age = Integer.parseInt(petMap.get("p_age"));
		
		int result = userPetInfoService.updatePetModifyInfo(p_no, p_name, p_species, p_age);
		
		map.put("success", result > 0);
		
		return map;
	}
	
	@PostMapping("/modify_value")
	@ResponseBody
	public Object petModifyValue(@RequestBody Map<String, String> pNoMap) {
		log.info("petModifyValue()");
		
		Map<String, Object> resultMap = new HashMap<>();
		
		UserPetInfoDto userPetInfoDto = userPetInfoService.selectPetForModify(Integer.parseInt(pNoMap.get("p_no")));
		
		if(userPetInfoDto != null) {
			resultMap.put("success", true);
			resultMap.put("userPetInfoDto", userPetInfoDto);
		} else {
			resultMap.put("success", false);
		}
		
		
		return resultMap;
	}
	
	@PostMapping("/main_pet_register")
	@ResponseBody
	public Object mainPetRegister(@RequestBody Map<String, String> pNoMap, HttpSession session) {
		log.info("mainPetRegister()");
		
		Map<String, Object> resultMap = new HashMap<>();
		MemberDto memberDto = (MemberDto) session.getAttribute("loginedMemberDto");
		String u_id = memberDto.getU_id();
		int p_no = Integer.parseInt(pNoMap.get("p_no"));
		
		int result = userPetInfoService.updateMainPet(u_id, p_no);
		resultMap.put("success", result > 0);
		
		
		return resultMap;
	}
	
	@PostMapping("/delete_pet")
	@ResponseBody
	public Object deletePet(@RequestBody Map<String, String> pNoMap) {
		log.info("deletePet()");
		
		Map<String, Object> resultMap = new HashMap<>();
		
		int p_no = Integer.parseInt(pNoMap.get("p_no"));
		
		int result = userPetInfoService.deletePet(p_no);
		resultMap.put("success", result > 0);
		
		return resultMap;
	}
	
}
