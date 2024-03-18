package com.dogdam.shop.user.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogdam.shop.admin.goods.GoodsDto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserGoodsService {

	@Autowired
	IUserGoodsDaoForMybatis goodsDao;

	public List<GoodsDto> userGoodsList() {
		log.info("userGoodsList()");

		return goodsDao.userGoodsList();
	}

	public GoodsDto userGoodsDetatilView(int g_no) {
		log.info("userGoodsDetatilView()");

		return goodsDao.userGoodsDetatilView(g_no);
	}

	public List<GoodsDto> searchGoodsConfirm(GoodsDto goodsDto) {
		log.info("searchGoodsConfirm()");

		return goodsDao.searchGoodsConfirm(goodsDto);
	}

	public GoodsDto selectByGNo(int g_no) {
		log.info("selectByGNo()");
		
		return goodsDao.selectByGNo(g_no);
	}

	
	/*여기는 그거 메인 상품*/
	public List<GoodsDto> orderByRegDate() {
		log.info("orderByRegDate()");
		
		return goodsDao.orderByRegDate();
	}

	public List<GoodsDto> orderByBookmark() {
		log.info("orderByBookmark()");
		
		return goodsDao.orderByBookmark();
	}

	public List<GoodsDto> orderByBuy() {
		log.info("orderByBuy()");
		
		return goodsDao.orderByBuy();
	}
	
	// 사료
	public List<GoodsDto> searchCategory(int c_no) {
		log.info("searchCategory()");
		
		return goodsDao.searchCategory(c_no);
	}
	
	public List<GoodsDto> searchProductsByCategory(int c_no) {
		log.info("searchProductsByCategory()");
		
		return goodsDao.searchProductsByCategory(c_no);
	}


}
