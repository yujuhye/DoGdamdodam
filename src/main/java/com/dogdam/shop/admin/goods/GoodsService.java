package com.dogdam.shop.admin.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogdam.shop.admin.category.CategoryDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class GoodsService {

	@Autowired
	IGoodsDaoForMybatis goodsDao;

	final static public int GOODS_INSERT_SUCCESS = 1;
	final static public int GOODS_INSERT_FAIL = 0;

	// 상품 조회
	public List<GoodsDto> selectList() {
		log.info("selectList()");
		
		return goodsDao.selectList();

	}

	public int insertGoods(GoodsDto goodsDto, int c_no) {
		log.info("insertGoods()");
		
		Map<String, Object> map = new HashMap<>();
		map.put("goodsDto", goodsDto);
		map.put("c_no", c_no);
		
		int result = goodsDao.insertGoods(map);
		
		if(result > 0)
			return GOODS_INSERT_SUCCESS;
		else 
			return GOODS_INSERT_FAIL;
		
	}

	// 상품 상세화면
	public GoodsDto goodsDetailView(int g_no) {
		log.info("goodsDetailView()");
		
		return goodsDao.goodsDetailView(g_no);
		
	}

	// 상품 수정 폼
//	public GoodsDto modifyGoods(int g_no) {
//		log.info("modifyGoods()");
//		
//		return goodsDao.selectGoods(g_no);
//		
//	}
	
	public GoodsDto modifyGoods(int g_no) {
		log.info("modifyGoods()");
		
		/* Map<String, Object> map = new HashMap<>();
		map.put("g_no", g_no);
		map.put("c_no", c_no); */
		
		return goodsDao.selectGoods(g_no);
		
	}
	
	
	// 상품 수정 confirm
	public int modifyGoodsConfirm(GoodsDto goodsDto) {
		log.info("modifyGoodsConfirm()");
		
		/* Map<String, Object> map = new HashMap<>();
		map.put("goodsDto", goodsDto);
		map.put("c_no", c_no); */
		
		return goodsDao.modifyGoodsConfirm(goodsDto);
	}

	public int deleteGoodsConfirm(int g_no) {
		log.info("deleteGoodsConfirm()");
		
		return goodsDao.deleteGoodsConfirm(g_no);
	}

	// 상품정보 조회
	public List<CategoryDto> getGoodsInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateApproval(int g_no) {
		log.info("updateApproval()");
		
		return goodsDao.updateApproval(g_no);
	}
	

}
