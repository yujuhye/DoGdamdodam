package com.dogdam.shop.user.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ReviewDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<ReviewDto> selectList(int g_no) {
		log.info("selectList()");
		
		String sql  = "SELECT * FROM REVIEW "
					+ "WHERE G_NO = ? "
					+ "ORDER BY R_NO DESC";
		
		List<ReviewDto> reviewDtos = new ArrayList<>();
		
		try {
			
			reviewDtos = jdbcTemplate.query(sql, new RowMapper<ReviewDto>() {

				@Override
				public ReviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					ReviewDto reviewDto = new ReviewDto();
					
					reviewDto.setR_no(rs.getInt("R_NO"));
					reviewDto.setG_no(rs.getInt("G_NO"));
					reviewDto.setU_id(rs.getString("U_ID"));
					reviewDto.setR_text(rs.getString("R_TEXT"));
					reviewDto.setR_thumbnail_name(rs.getString("R_THUMBNAIL_NAME"));
					reviewDto.setR_second_name(rs.getString("R_SECOND_NAME"));
					reviewDto.setR_third_name(rs.getString("R_THIRD_NAME"));
					reviewDto.setR_last_name(rs.getString("R_LAST_NAME"));
					reviewDto.setR_rating(rs.getFloat("R_RATING"));
					reviewDto.setR_reg_date(rs.getString("R_REG_DATE"));
					reviewDto.setR_mod_date(rs.getString("R_MOD_DATE"));
					
					return reviewDto;
				}
				
			}, g_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reviewDtos.size() > 0 ? reviewDtos : null;
	}

	public int insertReview(ReviewDto reviewDto) {
		log.info("insertReview()");
		
		String sql  = "INSERT INTO REVIEW(U_ID, G_NO, R_TEXT, R_THUMBNAIL_NAME, R_SECOND_NAME, R_THIRD_NAME, R_LAST_NAME, R_RATING, R_REG_DATE, R_MOD_DATE) "
					+ "SELECT ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() "
					+ "FROM GOODS "
					+ "WHERE G_NO = ? AND EXISTS (SELECT 1 FROM `USER` WHERE U_ID = ?)";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, reviewDto.getU_id(), reviewDto.getG_no(), reviewDto.getR_text(), reviewDto.getR_thumbnail_name(), reviewDto.getR_second_name(),
                    reviewDto.getR_third_name(), reviewDto.getR_last_name(), reviewDto.getR_rating(), reviewDto.getG_no(), reviewDto.getU_id());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
	}

	public ReviewDto reviewDetailView(int r_no) {
		log.info("reviewDetailView()");
		
		String sql = "SELECT * FROM REVIEW " + "ORDER BY R_NO DESC";

		List<ReviewDto> reviewDtos = new ArrayList<>();

		try {

			reviewDtos = jdbcTemplate.query(sql, new RowMapper<ReviewDto>() {

				@Override
				public ReviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {

					ReviewDto reviewDto = new ReviewDto();

					reviewDto.setR_no(rs.getInt("R_NO"));
					reviewDto.setG_no(rs.getInt("G_NO"));
					reviewDto.setU_id(rs.getString("U_ID"));
					reviewDto.setR_text(rs.getString("R_TEXT"));
					reviewDto.setR_thumbnail_name(rs.getString("R_THUMBNAIL_NAME"));
					reviewDto.setR_second_name(rs.getString("R_SECOND_NAME"));
					reviewDto.setR_third_name(rs.getString("R_THIRD_NAME"));
					reviewDto.setR_last_name(rs.getString("R_LAST_NAME"));
					reviewDto.setR_rating(rs.getFloat("R_RATING"));
					reviewDto.setR_reg_date(rs.getString("R_REG_DATE"));
					reviewDto.setR_mod_date(rs.getString("R_MOD_DATE"));

					return reviewDto;
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reviewDtos.size() > 0 ? reviewDtos.get(0) : null;

	}

	public ReviewDto selectReview(int r_no) {
		log.info("selectReview()");
		String sql  = "SELECT * FROM REVIEW " 
					+ "WHERE R_NO = ?";

		List<ReviewDto> reviewDtos = new ArrayList<>();

		try {

			reviewDtos = jdbcTemplate.query(sql, new RowMapper<ReviewDto>() {

				@Override
				public ReviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {

					ReviewDto reviewDto = new ReviewDto();

					reviewDto.setR_no(rs.getInt("R_NO"));
					reviewDto.setG_no(rs.getInt("G_NO"));
					reviewDto.setU_id(rs.getString("U_ID"));
					reviewDto.setR_text(rs.getString("R_TEXT"));
					reviewDto.setR_thumbnail_name(rs.getString("R_THUMBNAIL_NAME"));
					reviewDto.setR_second_name(rs.getString("R_SECOND_NAME"));
					reviewDto.setR_third_name(rs.getString("R_THIRD_NAME"));
					reviewDto.setR_last_name(rs.getString("R_LAST_NAME"));
					reviewDto.setR_rating(rs.getFloat("R_RATING"));
					reviewDto.setR_reg_date(rs.getString("R_REG_DATE"));
					reviewDto.setR_mod_date(rs.getString("R_MOD_DATE"));

					return reviewDto;
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reviewDtos.size() > 0 ? reviewDtos.get(0) : null;

	}

	public int modifyReviewConfirm(ReviewDto reviewDto) {
		log.info("modifyReviewConfirm()");

		List<String> args = new ArrayList<String>();

		String sql = "UPDATE REVIEW SET ";
		sql += "R_TEXT = ?, ";
		args.add(reviewDto.getR_text());

		if (reviewDto.getR_thumbnail_name() != null) {

			sql += "R_THUMBNAIL_NAME = ?, ";
			args.add(reviewDto.getR_thumbnail_name());

		}

		if (reviewDto.getR_second_name() != null) {

			sql += "R_SECOND_NAME = ?, ";
			args.add(reviewDto.getR_second_name());

		}

		if (reviewDto.getR_third_name() != null) {

			sql += "R_THIRD_NAME = ?, ";
			args.add(reviewDto.getR_third_name());

		}

		if (reviewDto.getR_last_name() != null) {

			sql += "R_LAST_NAME = ?, ";
			args.add(reviewDto.getR_last_name());

		}

		sql += "R_RATING = ?, ";
		args.add(Float.toString(reviewDto.getR_rating()));

		sql += "R_MOD_DATE = MOW() ";

		sql += "WHERE R_NO = ?";
		args.add(Integer.toString(reviewDto.getR_no()));

		int result = -1;

		try {

//			result = jdbcTemplate.update(sql, reviewDto.getR_text(), reviewDto.getR_thumbnail_name(),
//					reviewDto.getR_second_name(), reviewDto.getR_third_name(), reviewDto.getR_last_name(),
//					reviewDto.getR_rating());

			result = jdbcTemplate.update(sql, args.toArray());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int reviewDeleteConfirm(int r_no) {
		log.info("reviewDeleteConfirm()");
		
		String sql = "DELETE FROM REVIEW WHERE R_NO = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, r_no);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
	}

	// 본인이 작성한 리뷰만 확인하기(my page)
	public List<ReviewDto> reviewAllSelect(String u_id) {
		log.info("reviewAllSelect()");
		
		String sql  = "SELECT * FROM REVIEW "
					+ "WHERE U_ID = ? "
					+ "ORDER BY R_NO DESC";
	
		List<ReviewDto> reviewDtos = new ArrayList<>();
		
		try {
			
			reviewDtos = jdbcTemplate.query(sql, new RowMapper<ReviewDto>() {
	
				@Override
				public ReviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					ReviewDto reviewDto = new ReviewDto();
					
					reviewDto.setR_no(rs.getInt("R_NO"));
					reviewDto.setG_no(rs.getInt("G_NO"));
					reviewDto.setU_id(rs.getString("U_ID"));
					reviewDto.setR_text(rs.getString("R_TEXT"));
					reviewDto.setR_thumbnail_name(rs.getString("R_THUMBNAIL_NAME"));
					reviewDto.setR_second_name(rs.getString("R_SECOND_NAME"));
					reviewDto.setR_third_name(rs.getString("R_THIRD_NAME"));
					reviewDto.setR_last_name(rs.getString("R_LAST_NAME"));
					reviewDto.setR_rating(rs.getFloat("R_RATING"));
					reviewDto.setR_reg_date(rs.getString("R_REG_DATE"));
					reviewDto.setR_mod_date(rs.getString("R_MOD_DATE"));
					
					return reviewDto;
				}
				
			}, u_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	return reviewDtos.size() > 0 ? reviewDtos : null;
}

	public List<ReviewDto> seletStar() {
		log.info("seletStar()");
		
		String sql = "SELECT G_NO, AVG(R_RATING) AS r_rating FROM REVIEW GROUP BY G_NO ";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ReviewDto.class));
	}

	public List<ReviewDto> userReviewAllSelect(String u_id) {
	    log.info("userReviewAllSelect()");

	    String sql  = "SELECT r.*, g.G_NAME, g.G_THUMBNAIL_NAME "
	    		+ "FROM REVIEW r "
	    		+ "JOIN GOODS g ON r.G_NO = g.G_NO "
	    		+ "WHERE r.U_ID = ? "
	    		+ "ORDER BY r.R_NO DESC";

	    List<ReviewDto> reviewDtos = new ArrayList<>();

	    try {
	        reviewDtos = jdbcTemplate.query(sql, new RowMapper<ReviewDto>() {
	            @Override
	            public ReviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	                ReviewDto reviewDto = new ReviewDto();
	                reviewDto.setR_no(rs.getInt("R_NO"));
	                reviewDto.setG_no(rs.getInt("G_NO"));
	                reviewDto.setU_id(rs.getString("U_ID"));
	                reviewDto.setR_text(rs.getString("R_TEXT"));
	                reviewDto.setR_thumbnail_name(rs.getString("R_THUMBNAIL_NAME"));
	                reviewDto.setR_second_name(rs.getString("R_SECOND_NAME"));
	                reviewDto.setR_third_name(rs.getString("R_THIRD_NAME"));
	                reviewDto.setR_last_name(rs.getString("R_LAST_NAME"));
	                reviewDto.setR_rating(rs.getFloat("R_RATING"));
	                reviewDto.setR_reg_date(rs.getString("R_REG_DATE"));
	                reviewDto.setR_mod_date(rs.getString("R_MOD_DATE"));
	                return reviewDto;
	            }
	        }, u_id);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return reviewDtos.size() > 0 ? reviewDtos : null;
	}

	// 수정 정보 불러오기
	public ReviewDto selectModifyReview(int r_no) {
		log.info("selectModifyReview()");
		
		String sql  = "SELECT * FROM REVIEW "
					+ "WHERE R_NO = ?";
		
		List<ReviewDto> reviewDtos = new ArrayList<>();
		
		try {
			
			reviewDtos = jdbcTemplate.query(sql, new RowMapper<>() {

				@Override
				public ReviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					ReviewDto reviewDto = new ReviewDto();
					
	                reviewDto.setR_no(rs.getInt("R_NO"));
	                reviewDto.setG_no(rs.getInt("G_NO"));
	                reviewDto.setU_id(rs.getString("U_ID"));
	                reviewDto.setR_text(rs.getString("R_TEXT"));
	                reviewDto.setR_thumbnail_name(rs.getString("R_THUMBNAIL_NAME"));
	                reviewDto.setR_second_name(rs.getString("R_SECOND_NAME"));
	                reviewDto.setR_third_name(rs.getString("R_THIRD_NAME"));
	                reviewDto.setR_last_name(rs.getString("R_LAST_NAME"));
	                reviewDto.setR_rating(rs.getFloat("R_RATING"));
	                reviewDto.setR_reg_date(rs.getString("R_REG_DATE"));
	                reviewDto.setR_mod_date(rs.getString("R_MOD_DATE"));
	                
	                return reviewDto;
				}
				
			}, r_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reviewDtos.size() > 0 ? reviewDtos.get(0) : null;
	}
	

}
