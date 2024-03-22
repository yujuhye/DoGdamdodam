package com.dogdam.shop.user.member.petinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserPetInfoService {

	@Autowired
	IUserPetInfoDaoForMybatis userPetInfoDao;

	public Map<String, Object> selectUserPetInfos(String u_id) {
		log.info("selectUserPetInfos()");
		
		Map<String, Object> map = new HashMap<>();
		
		UserPetInfoDto petInfoDto = userPetInfoDao.selectUserMainPet(u_id);
		
		if(petInfoDto == null) {
			map.put("petInfoDto", null);
		} else {
			map.put("petInfoDto", petInfoDto);			
		}
		
		
		List<UserPetInfoDto> userPetInfoDtos = userPetInfoDao.selectUserPetInfos(u_id);
		
		if(userPetInfoDtos.size() > 0) {
			for(int i = 0; i < userPetInfoDtos.size(); i++) {
				
				UserPetInfoDto userPetInfoDto = userPetInfoDtos.get(i);
			}
			
		}

		map.put("userPetInfoDtos", userPetInfoDtos);
		
		return map;
	}

	public int insertNewPetInfo(String u_id, String p_name, String p_species, int p_age) {
		log.info("insertNewPetInfo()");
		
		Map<String, Object> map = new HashMap<>();
		map.put("u_id", u_id);
		map.put("p_name", p_name);
		map.put("p_species", p_species);
		map.put("p_age", p_age);
		
		
		return userPetInfoDao.insertNewPetInfo(map);
	}

	public UserPetInfoDto selectPetForModify(int p_no) {
		log.info("selectPetForModify()");
		
		return userPetInfoDao.selectPetForModify(p_no);
	}

	public int updatePetModifyInfo(int p_no, String p_name, String p_species, int p_age) {
		log.info("updatePetModifyInfo()");
		
		Map<String, Object> map = new HashMap<>();
		map.put("p_no", p_no);
		map.put("p_name", p_name);
		map.put("p_species", p_species);
		map.put("p_age", p_age);
		
		return userPetInfoDao.updatePetModifyInfo(map);
	}


	public UserPetInfoDto updateMainPet(String u_id, int p_no) {
		log.info("updateMainPet()");
		
		Map<String, Object> map = new HashMap<>();
		map.put("u_id", u_id);
		map.put("p_no", p_no);
		/* userPetInfoDao.updateHasMainPetStep(u_id); */
		
		int result = userPetInfoDao.updateMainPetRegister(map); 
		
		return result > 0 ? userPetInfoDao.getLatesupdateMainPet(map) : null;			
	}

	public UserPetInfoDto deletePet(String u_id, int p_no) {
		log.info("deletePet()");
		Map<String, Object> map = new HashMap<>();
		map.put("u_id", u_id);
		map.put("p_no", p_no);
		
		int result = userPetInfoDao.deletePet(p_no);
		
		return result > 0 ?  userPetInfoDao.getLatesupdateMainPet(map) : null;
	}
	
	
	
}
