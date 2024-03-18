package com.dogdam.shop.user.member;

import lombok.Data;

@Data
public class MemberDto {
	
	private int u_no;
	private String u_id;
	private String u_name;
	private String u_pw	;
	private String u_nickname;
	private String u_mail;
	private String u_phone;
    private int u_grade;
    private String u_reg_date;
    private String u_mod_date;

}
