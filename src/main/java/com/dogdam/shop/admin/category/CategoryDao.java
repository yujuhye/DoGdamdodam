package com.dogdam.shop.admin.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CategoryDao {
	
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 대분류와 중분류 카테고리 목록 가져오기
    public List<CategoryDto> getCategoriesByParentsNo(int parentsNo) {
        log.info("getCategoriesByParentsNo()");
        
        String sql = "SELECT * FROM CATEGORY WHERE C_PARENTS_NO = ?";
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, parentsNo);
        List<CategoryDto> categories = new ArrayList<>();
        
        for (Map<String, Object> row : rows) {
            CategoryDto category = new CategoryDto();
            category.setC_no((Integer) row.get("C_NO"));
            category.setC_name((String) row.get("C_NAME"));
            category.setC_reg_date((String) row.get("C_REG_DATE"));
            category.setC_mod_date((String) row.get("C_MOD_DATE"));
            
            categories.add(category);
        }
        
        return categories;
    }

    public List<CategoryDto> getSecondaryCategoriesByParentsNo(int c_no) {
        log.info("getSecondaryCategoriesByParentsNo()");
        log.info("c_no >>>>>>>>>> " + c_no);
        
        String sql = "SELECT * FROM CATEGORY WHERE C_PARENTS_NO = ?";
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, c_no);
        List<CategoryDto> categories = new ArrayList<>();
        
        for (Map<String, Object> row : rows) {
            CategoryDto category = new CategoryDto();
            category.setC_no((Integer) row.get("C_NO"));
            category.setC_name((String) row.get("C_NAME"));
            category.setC_reg_date((String) row.get("C_REG_DATE"));
            category.setC_mod_date((String) row.get("C_MOD_DATE"));
            
            categories.add(category);
        }
        return categories;
    }
}