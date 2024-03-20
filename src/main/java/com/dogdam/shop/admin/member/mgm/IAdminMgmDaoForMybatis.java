package com.dogdam.shop.admin.member.mgm;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dogdam.shop.admin.CurrentSetPage;
import com.dogdam.shop.admin.member.AdminMemberDto;
import com.dogdam.shop.user.member.MemberDto;

@Mapper
public interface IAdminMgmDaoForMybatis {

	
	public int updateForApproval(int a_no);
	
	public List<MemberDto> getAllUserItme(CurrentSetPage currentPaging);
	public int getTotalPageNo();
	public int getTotalNoBySearch(@Param("searchText") String searchText);
	public List<MemberDto> searchUserItem(@Param("skipPage") int skipPage, @Param("amount") int amount, @Param("searchText") String searchText);
	
	public List<AdminMemberDto> getAllAdminItme(CurrentSetPage currentSetPage);
	public int getTotalAdminPage();
	public List<AdminMemberDto> searchAdminItem(@Param("skipPage") int skipPage, @Param("amount") int amount, @Param("searchText") String searchText);
	public int getTotalNoBySearchAdmin(String searchText);
	
	public List<AdminMemberDto> getApprovedAdminItme(CurrentSetPage currentSetPage);
	public int getTotalApprovedAdminPage();
	public List<AdminMemberDto> searchApprovedAdminItem(@Param("skipPage") int skipPage, @Param("amount") int amount, @Param("searchText") String searchText);
	public int getTotalNoBySearchApprovedAdmin(String searchText);

	public List<AdminMemberDto> getWaitingAdminItme(CurrentSetPage currentSetPage);
	public int getTotalWaitingAdminPage();
	public List<AdminMemberDto> searchWaitingAdminItme(@Param("skipPage") int skipPage, @Param("amount") int amount, @Param("searchText") String searchText);
	public int getTotalNoBySearchWaitingAdmin(String searchText);
	
}
