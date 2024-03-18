package com.dogdam.shop.user.review;

import lombok.Data;

@Data
public class ReviewDto {

	private int r_no;
//	private int g_no;
	private String u_id;
	private String r_text;
	private String r_thumbnail_name;
	private String r_second_name;
	private String r_third_name;
	private String r_last_name;
	private float r_rating;
	private String r_reg_date;
	private String r_mod_date;
	
	private int g_no;
    private String g_name;
    private String g_thumbnail_name;
    private String g_second_img_name;
    private String g_third_img_name;
    private String g_detail_content;
    private int c_no;
    private int c_parents_no;
    private String g_explanation;
    private int g_price;
    private int g_approval;
    private String g_reg_date;
    private String g_mod_date;
}
