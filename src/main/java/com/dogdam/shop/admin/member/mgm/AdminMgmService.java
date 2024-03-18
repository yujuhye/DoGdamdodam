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

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminMgmService {
	
	@Autowired
	IAdminMgmDaoForMybatis adminMgmDao;

	public List<AdminMemberDto> adminListup() {
		log.info("adminListup()");
		
		return adminMgmDao.selectAllAdmins();
	}
	
	public List<AdminMemberDto> adminNoArvList() {
		log.info("adminNoArvList()");
		
		return adminMgmDao.selectNoArvAdmins();
	}
	
	public List<AdminMemberDto> approvedList() {
		log.info("approvedList()");
		
		return adminMgmDao.selectApprovedAdmins();
	}
	
	

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

	public List<MemberDto> selectForAllUser() {
		log.info("selectForAllUser()");
		
		
		return adminMgmDao.selectForAllUser();
	}

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


	
			
			
		
		
		
		
		
	
	

	
	
	
}
