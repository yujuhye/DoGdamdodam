package com.dogdam.shop.user.goods;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dogdam.shop.admin.goods.GoodsDto;

@Mapper
public interface IUserGoodsDaoForMybatis {
	
	public List<GoodsDto> userGoodsList();
	public GoodsDto userGoodsDetatilView(int g_no);
	public List<GoodsDto> searchGoodsConfirm(GoodsDto goodsDto);
//	public List<GoodsDto> selectListByGNo(int r_no);
	public GoodsDto selectByGNo(int g_no);
	
	// 최신
	public List<GoodsDto> orderByRegDate();
	public List<GoodsDto> orderByBookmark();
	public List<GoodsDto> orderByBuy();
	
	// 카테고리
	public List<GoodsDto> searchCategory(int c_no);
	public List<GoodsDto> searchProductsByCategory(int c_no);
	
}
