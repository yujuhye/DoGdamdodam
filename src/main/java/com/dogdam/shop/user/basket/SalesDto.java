package com.dogdam.shop.user.basket;

import com.dogdam.shop.user.member.MemberDto;

import lombok.Data;

@Data
public class SalesDto {
	
	private int s_no;
	private String o_no;
	private String u_id;
    private int s_quantity;  
    private int sb_group_no	;
    private String s_return_config;
    private String s_reg_date;
    private String s_mod_date;
    
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
    
    private int da_no;
	private MemberDto memberDto;
	private String da_name;
	private String da_addr_phone;
	private String da_addr_text;
	private int da_default;
	private String da_reg_date;
	private String da_mod_date;
	
	/*review*/
	private int r_no;
	private String r_text;
	private String r_thumbnail_name;
	private String r_second_name;
	private String r_third_name;
	private String r_last_name;
	private float r_rating;
	private String r_reg_date;
	private String r_mod_date;
	private int r_confirm_rv;
	
}
