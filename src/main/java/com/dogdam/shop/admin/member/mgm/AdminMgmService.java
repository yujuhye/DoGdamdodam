package com.dogdam.shop.admin.member.mgm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogdam.shop.admin.CurrentSetPage;
import com.dogdam.shop.admin.PageMakerDto;
import com.dogdam.shop.admin.member.AdminMemberDto;
import com.dogdam.shop.user.member.MemberDto;
import com.dogdam.shop.user.member.petinfo.UserPetInfoDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminMgmService {
	
	@Autowired
	IAdminMgmDaoForMybatis adminMgmDao;
	

	public void approvalUpdate(int a_no) {
		log.info("approvalUpdate()");
		
		int result = adminMgmDao.updateForApproval(a_no);
		
		if(result > 0) {
			log.info("updateForApproval() success");
		} else {
			log.info("updateForApproval() faill");	
		}
		
	}


	public Map<String, Object> updateApproval(String approval, String aNo) {
		log.info("updateApproval()");
		
		HashMap<String, Object> map = new HashMap<>();
		
		int a_no = Integer.parseInt(aNo);
		String str = approval.toString();
		
		int result = -1;
		String approvalText = null;
		
		if(str.equals("대기")) {
			
			result = adminMgmDao.updateForApproval(a_no);
			
			if(result > 0) {
				approvalText = "승인";
			}
			
			map.put("success", result > 0);
			map.put("approvalText", approvalText);
			
			return map;
			
		} else if (str.equals("승인")) {
			
			result = adminMgmDao.updateForApproval(a_no);
			
			if(result > 0) {
				approvalText = "대기";
			}
			
			map.put("success", result > 0);
			map.put("approvalText", approvalText);
			
			return map;
			
		}

		return map;
		
	}

	// 유저 목록 페이징처리 스타트
	public Map<String, Object> getAllUserItme(int pageNum, int amount) {
		log.info("getAllUserItme()");
		
		Map<String, Object> map =  new HashMap<>();
		
		// 현재페이지에 랜더링할 아이템
		CurrentSetPage currentSetPage = new CurrentSetPage(pageNum, amount);
		
		List<MemberDto> memberDtos = adminMgmDao.getAllUserItme(currentSetPage);
		map.put("memberDtos", memberDtos);
		
		// 전체 페이지 개수
		int totalPageNo = adminMgmDao.getTotalPageNo();
		PageMakerDto pageMakerDto = new PageMakerDto(currentSetPage, totalPageNo);
		
		map.put("pageMakerDto", pageMakerDto);
		
		
		return map;
	}

	public Map<String, Object> searchUserItme(int pageNum, int amount, String searchText) {
		log.info("searchUserItme()");
		
		
		Map<String, Object> map = new HashMap<>();
		
		CurrentSetPage currentSetPage = new CurrentSetPage(pageNum, amount);
		int skipPage = currentSetPage.getSkipPage();
		
		List<MemberDto> memberDtos = adminMgmDao.searchUserItem(skipPage, amount, searchText);
		map.put("memberDtos", memberDtos);
		
		
		int totalPageNo = adminMgmDao.getTotalNoBySearch(searchText);
		PageMakerDto pageMakerDto = new PageMakerDto(currentSetPage, totalPageNo);
		
		map.put("pageMakerDto", pageMakerDto);
		
		
		return map;
	}
	// 유저 목록 페이징처리 엔드
	
	//어드민 페이징처리
	public Map<String, Object> getAllAdminItme(int pageNum, int amount) {
		log.info("getAllAdminItme()");
		
		Map<String, Object> map = new HashMap<>();
		
		CurrentSetPage currentSetPage = new CurrentSetPage(pageNum, amount);
		
		List<AdminMemberDto> adminDtos = adminMgmDao.getAllAdminItme(currentSetPage);
		map.put("adminDtos", adminDtos);
		
		int totalPageNo = adminMgmDao.getTotalAdminPage();
		PageMakerDto pageMakerDto = new PageMakerDto(currentSetPage, totalPageNo);
		map.put("pageMakerDto", pageMakerDto);
		
		
		return map;
	}

	public Map<String, Object> searchAdminItme(int pageNum, int amount, String searchText) {
		log.info("searchAdminItme()");
		
		Map<String, Object> map = new HashMap<>();
		
		CurrentSetPage currentSetPage = new CurrentSetPage(pageNum, amount);
		int skipPage = currentSetPage.getSkipPage();
		
		List<AdminMemberDto> adminDtos = adminMgmDao.searchAdminItem(skipPage, amount, searchText);
		map.put("adminDtos", adminDtos);
		
		int totalPageNo = adminMgmDao.getTotalNoBySearchAdmin(searchText);
		PageMakerDto pageMakerDto = new PageMakerDto(currentSetPage, totalPageNo);
		map.put("pageMakerDto", pageMakerDto);
		
		return map;
	}

	public Map<String, Object> getApprovedAdminItme(int pageNum, int amount) {
		log.info("getApprovedAdminItme()");
		
		Map<String, Object> map = new HashMap<>();
		
		CurrentSetPage currentSetPage = new CurrentSetPage(pageNum, amount);
		
		List<AdminMemberDto> adminDtos = adminMgmDao.getApprovedAdminItme(currentSetPage);
		map.put("adminDtos", adminDtos);
		
		int totalPageNo = adminMgmDao.getTotalApprovedAdminPage();
		PageMakerDto pageMakerDto = new PageMakerDto(currentSetPage, totalPageNo);
		map.put("pageMakerDto", pageMakerDto);
		
		
		return map;
	}

	public Map<String, Object> searchApprovedAdminItme(int pageNum, int amount, String searchText) {
		log.info("searchApprovedAdminItme()");
		
		Map<String, Object> map = new HashMap<>();
		
		CurrentSetPage currentSetPage = new CurrentSetPage(pageNum, amount);
		int skipPage = currentSetPage.getSkipPage();
		
		List<AdminMemberDto> adminDtos = adminMgmDao.searchApprovedAdminItem(skipPage, amount, searchText);
		map.put("adminDtos", adminDtos);
		
		int totalPageNo = adminMgmDao.getTotalNoBySearchApprovedAdmin(searchText);
		PageMakerDto pageMakerDto = new PageMakerDto(currentSetPage, totalPageNo);
		map.put("pageMakerDto", pageMakerDto);
		
		return map;
	}


	public Map<String, Object> getWaitingAdminItme(int pageNum, int amount) {
		log.info("getWaitingAdminItme()");
		
		Map<String, Object> map = new HashMap<>();
		
		CurrentSetPage currentSetPage = new CurrentSetPage(pageNum, amount);
		
		List<AdminMemberDto> adminDtos = adminMgmDao.getWaitingAdminItme(currentSetPage);
		map.put("adminDtos", adminDtos);
		
		int totalPageNo = adminMgmDao.getTotalWaitingAdminPage();
		PageMakerDto pageMakerDto = new PageMakerDto(currentSetPage, totalPageNo);
		map.put("pageMakerDto", pageMakerDto);
		
		
		return map;
	}


	public Map<String, Object> searchWaitingAdminItme(int pageNum, int amount, String searchText) {
		log.info("searchWaitingAdminItme()");
		
		Map<String, Object> map = new HashMap<>();
		
		CurrentSetPage currentSetPage = new CurrentSetPage(pageNum, amount);
		int skipPage = currentSetPage.getSkipPage();
		
		List<AdminMemberDto> adminDtos = adminMgmDao.searchWaitingAdminItme(skipPage, amount, searchText);
		map.put("adminDtos", adminDtos);
		
		int totalPageNo = adminMgmDao.getTotalNoBySearchWaitingAdmin(searchText);
		PageMakerDto pageMakerDto = new PageMakerDto(currentSetPage, totalPageNo);
		map.put("pageMakerDto", pageMakerDto);
		
		return map;
	}


	public MemberDto selectUserDto(int u_no) {
		log.info("selectUserDto()");
		return adminMgmDao.selectUserDto(u_no);
	}


	public UserPetInfoDto selectUserPetInfo(String u_id) {
		log.info("selectUserPetInfo()");
		return adminMgmDao.selectUserPetInfo(u_id);
	}


	
			
			
		
		
		
		
		
	
	

	
	
	
}
