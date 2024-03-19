package com.dogdam.shop.user.basket;

import lombok.Data;

@Data
public class BasketDto {

	private int sb_no;
	private String u_id;
	private int s_quantity;
    private int sb_group_no;
    private String sb_reg_date;
	
    private int g_no;
    private String g_name;
    private String g_thumbnail_name;
    private String g_second_img_name;
    private String g_third_img_name;
    private String g_detail_content;
    private int c_no;
    private String g_explanation;
    private int g_price;
    private int g_approval;
    private String g_reg_date;
    private String g_mod_date;
    
  
}
