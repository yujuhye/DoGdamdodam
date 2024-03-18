package com.dogdam.shop.user.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogdam.shop.admin.goods.GoodsDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;

	public GoodsDto getGoods(int g_no) {
		log.info("getGoods");
		
		return orderDao.getGoods(g_no);
	}

	
	public int calculateDonation(int g_no, int s_quantity) {
		log.info("calculateDonation");
		
		
		return orderDao.calculateDonation(g_no, s_quantity);
	}


	public int calculateorderAmount(int g_no, int s_quantity) {
		log.info("calculateorderAmount");
		
		
		return orderDao.calculateorderAmount(g_no, s_quantity);
	}


	public int donationConfirm(int g_no, int s_quantity, String u_id) {
		log.info("donationConfirm");
		
		 int result = -1;
		
		GoodsDto goodsDto = orderDao.getGoods(g_no);
		if(goodsDto != null) {
			int g_price = goodsDto.getG_price();
			
			result = orderDao.donationConfirm(g_price, s_quantity, u_id);
			if(result > 0) {
				log.info("DONATIONCONFIRM SUCCESS");
				
			} else {
				log.info("DONATIONCONFIRM FAIL");
			}
			
		}
		
		return result;
	}


	public AddressDto newAddressConfirm(AddressDto addressDto, String u_id) {
		log.info("newAddressConfirm");
		
		int result = orderDao.newAddressConfirm(addressDto, u_id);
		if(result > 0) {
			log.info("ADDRESSCONFIRM SUCCESS");
			
			 result = orderDao.updateAddressDefault(u_id);
			 if(result > 0) {
				 log.info("UPDATEADDRESSDEFAULT SUCCESS");
				 
				 AddressDto selectAddressDto = orderDao.selectAddress(u_id);
				 
				 return selectAddressDto;
			 } else {
				 log.info("UPDATEADDRESSDEFAULT FAIL");
			 }
			 
		} else {
			log.info("ADDRESSCONFIRM FAIL");
		}
		
		return null;
		 
	}


	public int orderConfirm(int s_quantity, int g_no, String u_id, int da_no) {
		log.info("orderConfirm");
		
		 int result = orderDao.orderConfirm(g_no, s_quantity, u_id, da_no);
		 if(result > 0) {
			 log.info("[OrderService] ORDERCONFIRM SUCCESS");
		 } else {
			 log.info("[OrderService] ORDERCONFIRM FAIL");

		 }
		 
		return result;
	}


	public AddressDto getAddressInfo(String u_id) {
		log.info("getAddressInfo");
		
		
		return orderDao.selectAddress(u_id);
	}


	public List<AddressDto> addresslist(String u_id) {
		log.info("addresslist");
		
		
		
		return orderDao.selectAddressList(u_id);
	}


	public int writeAddress(AddressDto addressDto, String u_id) {
		log.info("writeAddress");
		
		
		return orderDao.insertWriteAddress(addressDto, u_id);
	}


	public AddressDto sendSelectedAddress(int da_no) {
		log.info("sendSelectedAddress");
		
		return orderDao.selectedAddress(da_no);
	}



	






}
