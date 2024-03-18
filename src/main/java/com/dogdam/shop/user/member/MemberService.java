package com.dogdam.shop.user.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberService {

    @Autowired
    IUserMemberDaoForMybatis memberDao;
    
    public int selectForId(String u_id) {
		log.info("selectForId()");
		
		int result = memberDao.searchForDeleteMember(u_id);
		
		if(result > 0) {
			result = 1;
		} else {
			result = memberDao.searchForHasMember(u_id);
		}
		
		return result;
	}

    public int createAccountConfirm(MemberDto memberDto) {
      log.info("createAccountConfirm()");

      int result = -1;
      
      result = memberDao.insertMember(memberDto);

      return result;
      
     }


    public MemberDto loginConfirm(MemberDto memberDto) {
        log.info("loginConfirm()");

        return memberDao.selectMemberLogin(memberDto);
    }


    public MemberDto modfiyConfirm(MemberDto memberDto) {
        log.info("modfiyConfirm()");

        int result = memberDao.updateMemberModify(memberDto);

        return result > 0 ? memberDao.getLatestMemberInfo(memberDto) : null;
   }
    

    public int deleteConfirm(int u_no, String u_id) {
        log.info("deleteConfirm()");
        
        int result = -1;
        
        result = memberDao.deleteMember(u_no);
        
        if(result > 0) {
        	result = memberDao.insertDeleteMember(u_id);
        }
        
        return result;
   }


	





}