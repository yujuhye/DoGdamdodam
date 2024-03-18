package com.dogdam.shop.admin.member.mgm;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dogdam.shop.admin.CurrentSetPage;
import com.dogdam.shop.admin.member.AdminMemberDto;
import com.dogdam.shop.user.member.MemberDto;

@Mapper
public interface IAdminMgmDaoForMybatis {

	public List<AdminMemberDto> selectAllAdmins();
	public int updateForApproval(int a_no);
	public List<AdminMemberDto> selectNoArvAdmins();
	public List<AdminMemberDto> selectApprovedAdmins();
	public List<MemberDto> selectForAllUser();
	public List<MemberDto> getAllUserItme(CurrentSetPage currentPaging);
	public int getTotalPageNo();
	public int getTotalNoBySearch(@Param("searchText") String searchText);
	public List<MemberDto> searchUserItem(@Param("skipPage") int skipPage, @Param("amount") int amount, @Param("searchText") String searchText);
	
}
