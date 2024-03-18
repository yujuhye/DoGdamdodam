package com.dogdam.shop.user.member.petinfo;

import lombok.Data;

@Data
public class UserPetInfoDto {
	
	private int p_no;		
    private String u_id;		
    private String p_name;		
    private String p_species;	
    private int p_age;		
    private int p_step;		
    private String p_reg_date;
    private String p_mod_date;
}
