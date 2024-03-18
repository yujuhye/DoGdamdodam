package com.dogdam.shop.user.goods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.dogdam.shop.admin.goods.GoodsDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class UserGoodsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<GoodsDto> userGoodsList() {
		log.info("userGoodsList()");

		String sql = "SELECT * FROM GOODS "
					+ "WHERE G_APPROVAL = 1 " 
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

	public GoodsDto userGoodsDetatilView(int g_no) {
		log.info("userGoodsDetatilView()");
		
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
	
	public List<GoodsDto> searchGoodsConfirm(GoodsDto goodsDto) {
		log.info("searchGoodsConfirm()");

		String sql  = "SELECT * FROM GOODS " 
					+ "WHERE G_NAME LIKE ? AND G_APPROVAL = 1 " 
					+ "ORDER BY G_NO DESC";

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
