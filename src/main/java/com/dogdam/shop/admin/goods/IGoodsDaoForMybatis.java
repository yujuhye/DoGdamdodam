package com.dogdam.shop.admin.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dogdam.shop.admin.CurrentSetPage;

@Mapper
public interface IGoodsDaoForMybatis {
	
	public List<GoodsDto> selectList();
	
	// map으로 
	public int insertGoods(Map<String, Object> map);
	
	public GoodsDto goodsDetailView(int g_no);
//	public GoodsDto selectGoods(int g_no);
//	public GoodsDto selectGoods(Map<String, Object> map);
	//public int modifyGoodsConfirm(Map<String, Object> map);
	public GoodsDto selectGoods(int g_no);
	
	public int modifyGoodsConfirm(GoodsDto goodsDto);
	public int deleteGoodsConfirm(int g_no);
	public int updateApproval(int g_no);
	public List<GoodsDto> searchGoodsConfirm(GoodsDto goodsDto);

	public List<GoodsDto> getAllGoodsItem(CurrentSetPage currentSetPage);

	public int getTotalGoodsPage();

	public List<GoodsDto> searchGoodsItem(@Param("skipPage") int skipPage, @Param("amount") int amount, @Param("searchText") String searchText);

	public int getTotalNoBySearchGoods(String searchText);
	
	
}
