package com.dogdam.shop.admin.category;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICategoryDaoForMybatis {
	
    public List<CategoryDto> getCategoriesByParentsNo(int parentsNo);

    public List<CategoryDto> getSecondaryCategoriesByParentsNo(int c_no);
    
}