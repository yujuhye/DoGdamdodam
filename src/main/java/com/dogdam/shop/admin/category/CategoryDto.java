package com.dogdam.shop.admin.category;

import lombok.Data;

@Data
public class CategoryDto {
	
	private int c_no;
	private int c_parents_no;
    private String c_name;
    private String c_reg_date;
    private String c_mod_date;

}
