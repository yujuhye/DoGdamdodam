package com.dogdam.shop.admin.goods;

import lombok.Data;

@Data
public class GoodsDto {
   
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
    
    private int s_quantity;
    
    private int g_bm_count;
    private int g_review_cnt;
    
    /*review*/
    private int r_no;
	private String u_id;
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
