package com.dogdam.shop.user.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dogdam.shop.admin.goods.GoodsDto;

@Mapper
public interface IOrderDaoForMybatis {

	public GoodsDto getGoods(int g_no);
	
	public int calculateDonation(int g_no, int s_quantity);

	public int calculateorderAmount(int g_no, int s_quantity);

	public int donationConfirm(int g_price, int s_quantity, String u_id) ;

	public int newAddressConfirm(AddressDto addressDto, String u_id) ;

	public int updateAddressDefault(String u_id);
	public AddressDto selectAddress(String u_id);

	public int orderConfirm( int g_no, int s_quantity, String u_id, int da_no);

	public List<AddressDto> selectAddressList(String u_id) ;

	public int insertWriteAddress(AddressDto addressDto, String u_id) ;

	public AddressDto selectedAddress(int da_no) ;

}
