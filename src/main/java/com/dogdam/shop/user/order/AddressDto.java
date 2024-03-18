package com.dogdam.shop.user.order;

import com.dogdam.shop.user.member.MemberDto;

import lombok.Data;

@Data
public class AddressDto {
	
	private int da_no;
	private MemberDto memberDto;
	private String da_name;
	private String da_addr_phone;
	private String da_addr_text;
	private int da_default;
	private String da_reg_date;
	private String da_mod_date;

}
