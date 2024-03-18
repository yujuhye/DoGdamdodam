package com.dogdam.shop.admin.goods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.dogdam.shop.admin.category.CategoryDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class GoodsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<GoodsDto> selectList() {
		log.info("selectList()");

		String sql = "SELECT * FROM GOODS "
		           + "ORDER BY G_NO DESC";

		List<GoodsDto> goodsDtos = new ArrayList<GoodsDto>();

		try {

			goodsDtos = jdbcTemplate.query(sql, new RowMapper<GoodsDto>() {

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
					goodsDto.setC_parents_no(rs.getInt("C_PARENTS_NO"));
					goodsDto.setG_explanation(rs.getString("G_EXPLANATION"));
					goodsDto.setG_price(rs.getInt("G_PRICE"));
					goodsDto.setG_approval(rs.getInt("G_APPROVAL"));
					goodsDto.setG_reg_date(rs.getString("G_REG_DATE"));
					goodsDto.setG_mod_date(rs.getString("G_MOD_DATE"));

					return goodsDto;
				}

			});

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return goodsDtos.size() > 0 ? goodsDtos : null;

	}

	public int insertGoods(GoodsDto goodsDto, int c_no) {
		log.info("insertGoods()");
		
		String goodsSql = "INSERT INTO GOODS( " 
						+ "G_NAME, " 
						+ "G_THUMBNAIL_NAME, "
						+ "G_SECOND_IMG_NAME, "
						+ "G_THIRD_IMG_NAME, "
						+ "G_DETAIL_CONTENT, " 
						+ "C_NO, "
						+ "C_PARENTS_NO, "
						+ "G_EXPLANATION, " 
						+ "G_PRICE, " 
						+ "G_APPROVAL, " 
						+ "G_REG_DATE, " 
						+ "G_MOD_DATE) "
						+ "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";


		int result = -1;

		try {
			
			result = jdbcTemplate.update(goodsSql, goodsDto.getG_name(), goodsDto.getG_thumbnail_name(), goodsDto.getG_second_img_name(),
				    goodsDto.getG_third_img_name(), goodsDto.getG_detail_content(), c_no, goodsDto.getC_no(),
				    goodsDto.getG_explanation(), goodsDto.getG_price(), goodsDto.getG_approval());
			
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        
	    }

	    return result;
	}

	public GoodsDto goodsDetailView(int g_no) {
		log.info("goodsDetailView()");
		
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
					goodsDto.setC_parents_no(rs.getInt("C_PARENTS_NO"));
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

	public GoodsDto selectGoods(int g_no) {
		log.info("selectGoods()");
		
		String sql  = "SELECT * FROM GOODS "
					+ "WHERE G_NO = ?";
		
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
						goodsDto.setC_parents_no(rs.getInt("C_PARENTS_NO"));
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

	public int modifyGoodsConfirm(GoodsDto goodsDto) {
		log.info("modifyGoodsConfirm()");

		List<String> args = new ArrayList<String>();

		String sql = "UPDATE GOODS SET ";

		sql += "G_NAME = ?, ";
		args.add(goodsDto.getG_name());

		if (goodsDto.getG_thumbnail_name() != null) {
			sql += "G_THUMBNAIL_NAME = ?, ";
			args.add(goodsDto.getG_thumbnail_name());
		}

		if (goodsDto.getG_second_img_name() != null) {
			sql += "G_SECOND_IMG_NAME = ?, ";
			args.add(goodsDto.getG_second_img_name());
		}

		if (goodsDto.getG_third_img_name() != null) {
			sql += "G_THIRD_IMG_NAME = ?, ";
			args.add(goodsDto.getG_third_img_name());
		}

		if (goodsDto.getG_detail_content() != null) {
			sql += "G_DETAIL_CONTENT = ?, ";
			args.add(goodsDto.getG_detail_content());
		}

		sql += "C_NO = ?, ";
		args.add(Integer.toString(goodsDto.getC_no()));

		sql += "C_PARENTS_NO = ?, ";
		args.add(Integer.toString(goodsDto.getC_parents_no()));

		sql += "G_EXPLANATION = ?, ";
		args.add(goodsDto.getG_explanation());

		sql += "G_PRICE = ?, ";
		args.add(Integer.toString(goodsDto.getG_price()));
		
//		sql += "G_APPROVAL = ?, ";
//		args.add(Integer.toString(goodsDto.getG_approval()));

		sql += "G_MOD_DATE = NOW() ";
		
		sql += "WHERE G_NO = ? ";
		args.add(Integer.toString(goodsDto.getG_no()));

		int result = -1;

		try {
			
			result = jdbcTemplate.update(sql, args.toArray());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		return result;
	}

	public int deleteGoodsConfirm(int g_no) {
		log.info("deleteGoodsConfirm()");
		
		String sql = "DELETE FROM GOODS WHERE G_NO = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, g_no);
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return result;
	}

	public int updateApproval(int g_no) {
		log.info("updateApproval()");
		
		String sql  = "UPDATE GOODS SET "
					+ "G_APPROVAL = 1 "
					+ "WHERE G_NO = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, g_no);
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return result;
	}

	public List<GoodsDto> searchGoodsConfirm(GoodsDto goodsDto) {
		log.info("searchGoodsConfirm()");

		String sql = "SELECT * FROM GOODS " + "WHERE G_NAME LIKE ? " + "ORDER BY G_NO DESC";

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
					goodsDto.setC_parents_no(rs.getInt("C_PARENTS_NO"));
					goodsDto.setG_explanation(rs.getString("G_EXPLANATION"));
					goodsDto.setG_price(rs.getInt("G_PRICE"));
					goodsDto.setG_approval(rs.getInt("G_APPROVAL"));
					goodsDto.setG_reg_date(rs.getString("G_REG_DATE"));
					goodsDto.setG_mod_date(rs.getString("G_MOD_DATE"));

					return goodsDto;

				}

			}, "%" + goodsDto.getG_name() + "%");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return goodsDtos.size() > 0 ? goodsDtos : null;
	}

}
