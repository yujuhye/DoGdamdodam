package com.dogdam.shop.admin.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminDaoForMybatis {

	public int searchForDeleteMember(String a_id);
	public int searchForHasMember(String a_id);
	public int insertAdminMember(AdminMemberDto memberDto);
	public AdminMemberDto selectAdminForLogin(AdminMemberDto memberDto);
	public int insertSuperAdmin(AdminMemberDto memberDto);
	
	
}
