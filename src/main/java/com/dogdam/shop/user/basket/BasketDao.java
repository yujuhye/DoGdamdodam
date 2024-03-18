package com.dogdam.shop.user.basket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class BasketDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int basketConfirm(int g_no, int s_quantity,  String u_id) {
	      log.info("basketConfirm");
	      
	      String sql="INSERT INTO SHOPPING_BASKET( "
	            + "U_ID, "
	            + "S_QUANTITY, "
	            + "G_NO, "
	            + "SB_REG_DATE) "
	            + "VALUES(?, ?, ?, NOW())";
	      
	      int result = -1;
	      
	      try {
	         
	         result = jdbcTemplate.update(sql, u_id, s_quantity, g_no);
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return result;
	   }

	   public List<BasketDto> getAllBasketList(String u_id) {
	      log.info("getAllBasketList");
	      
	      String sql="SELECT G.G_NAME, G.G_PRICE, G.G_NO, G.G_THUMBNAIL_NAME, SB.S_QUANTITY "
	            + "FROM SHOPPING_BASKET AS SB "
	            + "JOIN GOODS AS G ON SB.G_NO = G.G_NO "
	            + "WHERE "
	            + "U_ID = ?";
	      
	      List<BasketDto> basketDtos = new ArrayList<>();
	      
	      try {
	         
	         basketDtos =jdbcTemplate.query(sql,  new BeanPropertyRowMapper<>(BasketDto.class), u_id);
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return basketDtos.size() > 0 ? basketDtos : null;
	   }

	   public int basketDelete(int g_no) {
	      log.info("basketDelete");
	      
	      String sql="DELETE FROM SHOPPING_BASKET "
	            + "WHERE "
	            + "G_NO = ?";
	      
	      int result = -1;
	      
	      try {
	         
	         result = jdbcTemplate.update(sql, g_no);
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return result;
	   }

	public int getQuantity(Integer g_no) {
		 log.info("getQuantity");
		 
		 String sql="SELECT S_QUANTITY FROM SHOPPING_BASKET "
		 		+ "WHERE "
		 		+ "G_NO = ?";
		 
		 Integer quantity = 0;
		 
		 try {
			 
			  quantity = jdbcTemplate.queryForObject(sql, Integer.class, g_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		return quantity;
	}

}
