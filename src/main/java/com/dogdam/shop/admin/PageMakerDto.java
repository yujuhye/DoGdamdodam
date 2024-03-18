package com.dogdam.shop.admin;

import lombok.Data;

@Data
public class PageMakerDto {

	private int startPage;
	private int endPage;
	private boolean	prev, next;
	private int total;
	private int totalPage;
	private CurrentSetPage currentSetPage;
	
	public PageMakerDto(CurrentSetPage currentSetPage, int total) {
		
		this.currentSetPage = currentSetPage;
		this.total = total;
		
		// 현재 페이지 번호와 페이지 셋 크기 (10)를 기반으로 endPage 계산
		this.endPage = (int) (Math.ceil(currentSetPage.getPageNum() / 5.0)) * 5;
		
		// endPage를 기반으로 startPage 계산
		this.startPage = this.endPage - 4;
		
		 // 총 데이터 개수와 페이지당 데이터 개수를 기반으로 실제 마지막 페이지 계산
		int realEnd = (int) (Math.ceil(total * 1.0 / currentSetPage.getAmount()));
		
		 // 실제 마지막 페이지가 endPage보다 작으면 endPage를 실제 마지막 페이지로 조정
		if(realEnd < this.endPage) this.endPage = realEnd;
		
		// startPage를 기반으로 이전 페이지 셋 존재 여부 판단
		this.prev =  this.startPage > 1;
		
		// endPage와 실제 마지막 페이지를 기반으로 다음 페이지 셋 존재 여부 판단
		this.next = this.endPage < realEnd;
		
		// 총 데이터 개수와 페이지당 데이터 개수를 기반으로 총 페이지 수 계산
		this.totalPage = total / currentSetPage.getAmount();
		
		// 페이지당 데이터 개수로 나누어 떨어지지 않는 데이터 개수가 존재하면 총 페이지 수에 1을 추가
		// 66.6666 = 67
		if(total % currentSetPage.getAmount() != 0) totalPage +=1;
		
	}
	
}
