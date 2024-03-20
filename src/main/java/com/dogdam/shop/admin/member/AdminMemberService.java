package com.dogdam.shop.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogdam.shop.admin.AdminConfig;
import com.dogdam.shop.admin.PasswordEncoderForAdmin;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminMemberService {

	@Autowired
	IAdminDaoForMybatis memberDao;
	
	public int selectForId(String a_id) {
		log.info("selectForId()");
		
		
		int result = memberDao.searchForDeleteMember(a_id);
		
		if(result > 0) {
			log.info("This ID exists in the deleted member.");
			result = 1;
		} else {
			result = memberDao.searchForHasMember(a_id);
			
			if(result <= 0) {
				log.info("This ID already exists");
		
			}
		}
		
		
		return result;
	}

	public int create_account_confirm(AdminMemberDto memberDto) {
		log.info("create_account_confirm()");
			
		int result = -1;
		String a_id = memberDto.getA_id();
		
		result = memberDao.searchForDeleteMember(a_id);
		if(result > 0) {
			return result = AdminConfig.CREATE_ACCOUNT_ERROR;
		} else {
			result = memberDao.searchForHasMember(a_id);
			if(result > 0) {
				return result = AdminConfig.CREATE_ACCOUNT_ERROR;
			} else {
				result = -1;
			}
		}
		
		String encryptedPassword = PasswordEncoderForAdmin.encodePassword(memberDto.getA_pw());
		memberDto.setA_pw(encryptedPassword);
		
		if(memberDto.getA_id().equals("superAdmin")) {
			result = memberDao.insertSuperAdmin(memberDto);
		} else {
			result = memberDao.insertAdminMember(memberDto);
		}
		
				
		if(result > 0) {
		log.info("Admin CreateAccount Success");
					
		return result = AdminConfig.CREATE_ACCOUNT_SUCCESS;
					
		} else {
		log.info("Admin CreateAccount Fail");
					
		return result = AdminConfig.CREATE_ACCOUNT_FAIL;
		}
		
	}

	public AdminMemberDto login_confirm(AdminMemberDto memberDto) {
		log.info("login_confirm()");
		
		AdminMemberDto loginAdminDto = memberDao.selectAdminForLogin(memberDto);
		
		if(loginAdminDto != null && PasswordEncoderForAdmin.maches(memberDto.getA_pw(), loginAdminDto.getA_pw())) {
			return loginAdminDto;
		}
		
		return null;
		
	}

	
	
}
