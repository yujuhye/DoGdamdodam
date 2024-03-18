package com.dogdam.shop.user.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.dogdam.shop.admin.goods.GoodsDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class OrderDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public GoodsDto getGoods(int g_no) {
		log.info("getGoods()");
		
		String sql = "SELECT * FROM GOODS WHERE G_NO = ?";
		List<GoodsDto> goodsDtos = new ArrayList<GoodsDto>();
		
		try {
			
			goodsDtos = jdbcTemplate.query(sql, new RowMapper<>() {

				@Override
				public GoodsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					GoodsDto goodsDto = new GoodsDto();
					
					goodsDto.setG_no(rs.getInt("G_NO"));
					goodsDto.setG_name(rs.getString("G_NAME"));
					goodsDto.setG_thumbnail_name(rs.getString("G_THUMBNAIL_NAME"));
					goodsDto.setG_second_img_name(rs.getString("G_SECOND_IMG_NAME"));
					goodsDto.setG_third_img_name(rs.getString("G_THIRD_IMG_NAME"));
					goodsDto.setG_detail_content(rs.getString("G_DETAIL_CONTENT"));
					goodsDto.setC_no(rs.getInt("C_NO"));
					goodsDto.setG_explanation(rs.getString("G_EXPLANATION"));
					goodsDto.setG_price(rs.getInt("G_PRICE"));
					goodsDto.setG_approval(rs.getInt("G_APPROVAL"));
					goodsDto.setG_reg_date(rs.getString("G_REG_DATE"));
					goodsDto.setG_mod_date(rs.getString("G_MOD_DATE"));

					return goodsDto;
					
				}
				
			}, g_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return goodsDtos.size() > 0 ? goodsDtos.get(0) : null;
		
		
	}

	public int calculateDonation(int g_no, int s_quantity) {
		log.info("calculateDonation()");
		
		String sql = "SELECT ROUND(G_PRICE * ? * 0.01)  "
				+ "FROM GOODS "
				+ "WHERE "
				+ "G_NO = ?";
		
		int donationAmount= 0;
		
		try {
			
		 donationAmount = jdbcTemplate.queryForObject(sql, Integer.class, s_quantity, g_no);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return donationAmount;
	}

	public int calculateorderAmount(int g_no, int s_quantity) {
		log.info("calculateorderAmount()");
		
		String sql = "SELECT ROUND((G_PRICE * ?) + 3000) "
				+ "FROM GOODS "
				+ "WHERE "
				+ "G_NO = ?";
		
		int orderAmount= 0;
		
		try {
			
			orderAmount = jdbcTemplate.queryForObject(sql, Integer.class, s_quantity, g_no);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return orderAmount;
		
	}

	public int donationConfirm(int g_price, int s_quantity, String u_id) {
		log.info("donationConfirm()");
		
		String sql="INSERT INTO DONATION( "
				+ "U_ID, "
				+ "D_DONATION, "
				+ "D_REG_DATE, "
				+ "D_MOD_DATE) "
				+ "VALUES(?, ROUND(?, 0), NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, u_id, (g_price * s_quantity * 0.01) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int newAddressConfirm(AddressDto addressDto, String u_id) {
		log.info("newAddressConfirm()");
		
		String sql="INSERT INTO DELIVERY_ADDR( "
				+ "U_ID, "
				+ "DA_NAME, "
				+ "DA_ADDR_PHONE, "
				+ "DA_ADDR_TEXT, "
				+ "DA_REG_DATE, "
				+ "DA_MOD_DATE) "
				+ "VALUES(?, ?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, u_id,
					addressDto.getDa_name(),
					addressDto.getDa_addr_phone(),
					addressDto.getDa_addr_text());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int updateAddressDefault(String u_id) {
		log.info("updateAddressDefault()");
		
		String sql="UPDATE DELIVERY_ADDR "
				+ "SET "
				+ "DA_DEFAULT = 1 "
				+ "WHERE "
				+ "U_ID = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, u_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public AddressDto selectAddress(String u_id) {
		log.info("selectAddress()");
		
		String sql="SELECT * FROM DELIVERY_ADDR "
				+ "WHERE "
				+ "U_ID = ? AND DA_DEFAULT = 1";
		
		List<AddressDto> addressDtos = new ArrayList<AddressDto>();
		
		try {
	
			 addressDtos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AddressDto.class), u_id);
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	
	return addressDtos.size() > 0 ? addressDtos.get(0) : null;
	
		
	}

	public int orderConfirm(int g_no, int s_quantity, String u_id, int da_no) {
		log.info("orderConfirm()");
		
		String sql="INSERT INTO SALES( "
				+ "G_NO, "
				+ "U_ID, "
				+ "DA_NO, "
				+ "S_QUANTITY,"
				+ "S_REG_DATE,"
				+ "S_MOD_DATE) "
				+ "VALUES(?, ?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, g_no, u_id, da_no, s_quantity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<AddressDto> selectAddressList(String u_id) {
		log.info("selectAddressList()");
		
		String sql="SELECT * FROM DELIVERY_ADDR "
				+ "WHERE "
				+ "U_ID = ?";
		
		List<AddressDto> addressDtos = new ArrayList<AddressDto>();
		
		try {
			
			 addressDtos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AddressDto.class), u_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return addressDtos.size() > 0 ? addressDtos : null;
	}

	public int insertWriteAddress(AddressDto addressDto, String u_id) {
		log.info("insertWriteAddress()");
		
		String sql="INSERT INTO DELIVERY_ADDR( "
				+ "U_ID, "
				+ "DA_NAME, "
				+ "DA_ADDR_PHONE, "
				+ "DA_ADDR_TEXT, "
				+ "DA_REG_DATE, "
				+ "DA_MOD_DATE) "
				+ "VALUES(?, ?, ?, ?, NOW(), NOW())";
				
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, u_id, 
					addressDto.getDa_name(),
					addressDto.getDa_addr_phone(), 
					addressDto.getDa_addr_text());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public AddressDto selectedAddress(int da_no) {
		log.info("selectedAddress()");
		
		String sql="SELECT * FROM DELIVERY_ADDR "
				+ "WHERE DA_NO = ?";
		
		List<AddressDto> addressDtos = new ArrayList<AddressDto>();
		
		try {
			
			 addressDtos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AddressDto.class), da_no);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return addressDtos.size() > 0 ? addressDtos.get(0) : null;
	}


	


}
