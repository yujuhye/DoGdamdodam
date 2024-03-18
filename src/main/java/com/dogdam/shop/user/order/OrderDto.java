package com.dogdam.shop.user.order;

import com.dogdam.shop.admin.goods.GoodsDto;

import lombok.Data;

@Data
public class OrderDto {
	
	private int s_no;
	private GoodsDto goodsDto;   //g_no
	private String u_id;
	private AddressDto addressDto;   //da_no
    private int s_quantity;  
    private int sb_group_no	;
    private String s_return_config;
    private String s_reg_date;
    private String s_mod_date;

}
