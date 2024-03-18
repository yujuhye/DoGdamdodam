package com.dogdam.shop.user.bookmark;

import com.dogdam.shop.admin.goods.GoodsDto;

import lombok.Data;

@Data
public class BookmarkDto {
	
			public int bm_no;
			public String u_id;
			public String bm_reg_date;
			
		    private int g_no;
		    private String g_name;
		    private String g_thumbnail_name;
		    private int g_price;	
		    
		    GoodsDto goodsDto;
		    
}