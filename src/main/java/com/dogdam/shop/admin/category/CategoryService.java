package com.dogdam.shop.admin.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CategoryService {
	
	@Autowired
	ICategoryDaoForMybatis categoryDao;

	// 대분류 목록 가져오기
    public List<CategoryDto> getPrimaryCategories() {
    	log.info("getPrimaryCategories()");
    	
        return categoryDao.getCategoriesByParentsNo(0); // 대분류는 c_parents_no가 0인 것들
    	
        
    }

    public List<CategoryDto> getSecondaryCategories(int c_no) {
        log.info("getSecondaryCategories()");
        
        log.info("c_no >>>>>>>>>> " + c_no);
        
		return categoryDao.getSecondaryCategoriesByParentsNo(c_no); // 대분류의 c_no와 같은 중분류
      
    }
    
	
}