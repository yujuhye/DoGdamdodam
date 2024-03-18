package com.dogdam.shop.admin.member.mgm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dogdam.shop.admin.member.AdminMemberDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class AdminMgmDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<AdminMemberDto> selectAllAdmins() {
		log.info("selectAllAdmins()");
		
		String sql = "SELECT * FROM ADMIN WHERE A_ID != 'superAdmin'";
		
		List<AdminMemberDto> adminMemberDtos = new ArrayList<>();
		
		try {
			
			RowMapper<AdminMemberDto> rowMapper = BeanPropertyRowMapper.newInstance(AdminMemberDto.class);
			
			adminMemberDtos = jdbcTemplate.query(sql, rowMapper);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return adminMemberDtos;
	}
	
	

	public int updateForApproval(int a_no) {
		log.info("updateForApproval()");
		
		String sql = "UPDATE ADMIN "
				+ "SET A_APPROVAL = CASE A_APPROVAL "
				+ "WHEN 0 THEN 1 "
				+ "ELSE 0 END, "
				+ "A_ARV_DATE = NOW() "
				+ "WHERE A_NO = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, a_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}



	public List<AdminMemberDto> selectNoArvAdmins() {
		log.info("selectNoArvAdmins()");
		
		String sql = "SELECT * FROM ADMIN WHERE A_ID != 'superAdmin' AND A_APPROVAL = 0";
		
		List<AdminMemberDto> adminMemberDtos = new ArrayList<>();
		
		try {
			
			RowMapper<AdminMemberDto> rowMapper = BeanPropertyRowMapper.newInstance(AdminMemberDto.class);
			
			adminMemberDtos = jdbcTemplate.query(sql, rowMapper);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return adminMemberDtos;
	}



	public List<AdminMemberDto> selectApprovedAdmins() {
		log.info("selectApprovedAdmins()");
		
		String sql = "SELECT * FROM ADMIN WHERE A_ID != 'superAdmin' AND A_APPROVAL = 1";
		
		List<AdminMemberDto> adminMemberDtos = new ArrayList<>();
		
		try {
			
			RowMapper<AdminMemberDto> rowMapper = BeanPropertyRowMapper.newInstance(AdminMemberDto.class);
			
			adminMemberDtos = jdbcTemplate.query(sql, rowMapper);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return adminMemberDtos;
	}


	
		
	


	

}
