package com.dogdam.shop.user.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberDaoForMybatis {


	int insertMember(MemberDto memberDto);

	MemberDto selectMemberLogin(MemberDto memberDto);

	int updateMemberModify(MemberDto memberDto);

	MemberDto getLatestMemberInfo(MemberDto memberDto);

	int deleteMember(int u_no);

	int searchForDeleteMember(String u_id);

	int searchForHasMember(String u_id);

	int insertDeleteMember(String u_id);

	MemberDto selectMemberForLogin(MemberDto memberDto);

}
