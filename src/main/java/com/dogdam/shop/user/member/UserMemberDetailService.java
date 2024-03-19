package com.dogdam.shop.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class UserMemberDetailService implements UserDetailsService {
	
	@Autowired 
	IUserMemberDaoForMybatis iUserMemberDaoForMybatis;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername");
		
		MemberDto memberDto = new MemberDto();
		memberDto.setU_id(username);
		
		MemberDto selectedMemberDto = 
				iUserMemberDaoForMybatis.selectMemberForLogin(memberDto);
		
		return User.builder()
				.username(selectedMemberDto.getU_id())
				.password(selectedMemberDto.getU_pw())
				.roles("USESR")
				.build();
		
	}

	
	
	
}
