package com.dogdam.shop.user.member.petinfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserPetInfoDaoForMybatis {

	List<UserPetInfoDto> selectUserPetInfos(String u_id);

	UserPetInfoDto selectUserMainPet(String u_id);

	int insertNewPetInfo(Map<String, Object> map);

	UserPetInfoDto selectPetForModify(int p_no);

	int updatePetModifyInfo(Map<String, Object> map);

	int updateMainPetRegister(Map<String, Object> map);

	int deletePet(int p_no);



}
