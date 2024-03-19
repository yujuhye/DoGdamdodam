package com.dogdam.shop.admin.qa;

import lombok.Data;

@Data
public class AQnaDto {
	
	private int gqa_no;
	private String u_id;
	private String gqa_select_title;
	private String gqa_input_title;
	private String gqa_inquiry;
	private String gqa_answer;
	private int gqa_complete;
	private String gqa_reg_date;
	private String gqa_answer_date;
	
	private String a_id;
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