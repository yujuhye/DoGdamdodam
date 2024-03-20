package com.dogdam.shop.user.basket;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dogdam.shop.user.order.AddressDto;

@Mapper
public interface IBasketDaoForMybatis {

	public int basketConfirm( Map<String, Object> basketMap);

	public List<BasketDto> getAllBasketList(String u_id);
	
	public int basketDelete(int g_no);

	public int getQuantity(Map<Object, Object> getQuantityMap);

	public AddressDto getAddressInfoById(String u_id);
	
	public String getLastOrderNumber();

	public int saveNewAddress(Map<String, Object> addressMap);

	public int orderConfirm(List<SalesDto> salesDtoList);

	public int donationSave(Map<Object, Object> donationMapd);

	public int orderDetailsConfirm(Map<String, Object> orderNumberMap);

	public List<SalesDto> getMyOrderList(String u_id);

	public int isBasketGNum(Map<Object, Object> checkBasketMap);

	public SalesDto getMyOrderByDate(String s_reg_date);

	public List<SalesDto> getmyOrderGoods(String s_reg_date);

	public int deleteBasketByOrder(int g_no);

	public List<AddressDto> getMyAddresslist(String u_id);

	public int saveAddress(Map<String, Object> addressConfirmMap);

	public List<SalesDto> getMySearchList(Map<String, Object> searchMap);
	
	
}
