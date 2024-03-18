package com.dogdam.shop.admin;

import lombok.Data;

@Data
public class CurrentSetPage {

	private int pageNum;
	private int amount;
	private int skipPage;
	
	public CurrentSetPage() {
		this(1, 20);
		this.skipPage = 0;
	}
	
	public CurrentSetPage(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skipPage = (pageNum -1) * amount;
		
	}
	
}
