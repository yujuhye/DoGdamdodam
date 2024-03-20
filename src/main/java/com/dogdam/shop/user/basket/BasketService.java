package com.dogdam.shop.user.basket;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogdam.shop.admin.goods.GoodsDto;
import com.dogdam.shop.admin.goods.GoodsService;
import com.dogdam.shop.user.order.AddressDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BasketService {

	@Autowired
	IBasketDaoForMybatis basketDao;
	@Autowired
	GoodsService goodsService;

	public int basketConfirm( Map<String, Object> basketMap) {
	      log.info("basketConfirm");
	      
	      return basketDao.basketConfirm(basketMap);
	      
	   }

	   public List<BasketDto> getAllBasketList(String u_id) {
	      log.info("getAllBasketList");
	      
	      return basketDao.getAllBasketList(u_id);
	   }

	   public int basketDelete(int g_no) {
	      log.info("basketDelete");
	      
	      return basketDao.basketDelete(g_no);
	      
	   }

	public int getQuantity(int g_no, String u_id) {
		 log.info("getQuantity");
		 
		 Map<Object, Object> getQuantityMap = new HashMap<>();
		 getQuantityMap.put("u_id", u_id);
		 getQuantityMap.put("g_no", g_no);
		  
		 
		 
		return basketDao.getQuantity(getQuantityMap);
	}

	public int basketOrderAmount(Map<Integer, GoodsDto> baksetgoodsMap) {
		log.info("basketOrderAmount");
		
		int baskettotalAmountExDel = 0;
		
		for (Map.Entry<Integer, GoodsDto> entry : baksetgoodsMap.entrySet()) {
			  int quantity = entry.getValue().getS_quantity(); 
			  int price = entry.getValue().getG_price(); 
			  baskettotalAmountExDel += quantity * price;
			}
		
		
		return baskettotalAmountExDel;
		
	}

	public int basketDonationAmount(int baskettotalAmountExDel) {
		log.info("basketDonationAmount");
		
		
		float basketDonationAmount =  baskettotalAmountExDel * 0.01f;
		
		return Math.round(basketDonationAmount);
	}

	public int basektOrderAmountAndDel(int baskettotalAmountExDel) {
		log.info("basektOrderAmountAndDel");
		
		int basketTotalAmount = 0;
		
		basketTotalAmount = baskettotalAmountExDel + 3000;
		
		return basketTotalAmount;
		
	}

	public AddressDto getAddressInfoById(String u_id) {
		log.info("getAddressInfo");
		
		
		return basketDao.getAddressInfoById(u_id);
	}

	//주문번호 생성
		public synchronized  String generateOrderNumber() {
			log.info("generateOrderNumber");
			
			String lastOrderNumber = basketDao.getLastOrderNumber();
			
			log.info("lastOrderNumber>>>>>>>>>>>>>>" + lastOrderNumber);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");
					
			String currentDate = dateFormat.format(new Date());	
			
			int sequenceNumber = 1;
			
		    if (lastOrderNumber != null) {
		    	
		    	sequenceNumber = Integer.parseInt(lastOrderNumber.substring(8)) + 1;
		    	
		    } 
			
			String newOrderNumber = currentDate + String.format("%05d", sequenceNumber);
			
			log.info("newOrderNumber>>>>" + newOrderNumber);
			
			return newOrderNumber;
		}

		public int saveNewAddress(AddressDto addressDto, String u_id) {
			log.info("saveNewAddress");
			
			  Map<String, Object> addressMap = new HashMap<>();
			  addressMap.put("u_id", u_id);
			  addressMap.put("addressDto", addressDto);
			  
			   basketDao.saveNewAddress(addressMap);
			 
			  int da_no = ((BigInteger) addressMap.get("da_no")).intValue();
			  
			return da_no; 
		}

		public int orderConfirm(List<SalesDto> salesDtoList) {
			log.info("orderConfirm");
			
			
			return basketDao.orderConfirm(salesDtoList);
		}

		public int donationSave(int d_donation, String u_id) {
			log.info("donationSave");
			
			 Map<Object, Object> donationMap = new HashMap<>();
			 donationMap.put("u_id", u_id);
			 donationMap.put("d_donation", d_donation);
		      
			
			return basketDao.donationSave(donationMap);
		}

		public int orderDetailsConfirm(String u_id, String newOrderNumber) {
			log.info("OrderDetailsConfirm");
			
			 Map<String, Object> orderNumberMap = new HashMap<>();
			 orderNumberMap.put("u_id", u_id);
			 orderNumberMap.put("newOrderNumber", newOrderNumber);
			
			return basketDao.orderDetailsConfirm(orderNumberMap);
		}
		

		public Map<String, List<SalesDto>> getMyOrderList(String u_id) {
			log.info("getMyOrderList");
			
			 List<SalesDto> salesDtos = basketDao.getMyOrderList(u_id);
			 
			 //그룹화
//			 Map<String, List<SalesDto>> orderNumbersGroupByOrderNumber = salesDtos.stream()
//			            .collect(Collectors.groupingBy(SalesDto::getO_no));
			 
		 Map<String, List<SalesDto>> orderNumbersGroupByDatetime = salesDtos.stream()
			            .collect(Collectors.groupingBy(SalesDto::getS_reg_date));
		
		 //내림차순
		 Map<String, List<SalesDto>> sortedOrderNumbersGroupByDatetime = new TreeMap<>(Comparator.reverseOrder());
			 sortedOrderNumbersGroupByDatetime.putAll(orderNumbersGroupByDatetime);
			 
			return sortedOrderNumbersGroupByDatetime;
		}

		public int isBasketGNum(int g_no, String u_id) {
			log.info("isBasketGNum");
			
			 Map<Object, Object> checkBasketMap = new HashMap<>();
			 checkBasketMap.put("u_id", u_id);
			 checkBasketMap.put("g_no", g_no);
			
			
			return basketDao.isBasketGNum(checkBasketMap);
		}

		public SalesDto myOrderDetail(String s_reg_date) {
			log.info("myOrderDetail");
			
			
			return basketDao.getMyOrderByDate(s_reg_date);
		}

		public List<SalesDto> myOrderGoods(String s_reg_date) {
			log.info("myOrderGoods");
			
			
			return basketDao.getmyOrderGoods(s_reg_date);
		}

		public int deleteBasketByOrder(int g_no) {
			log.info("deleteBasketByOrder");
			
			
			return basketDao.deleteBasketByOrder(g_no);
		}

		public List<AddressDto> myAddresslist(String u_id) {
			log.info("myAddresslist");
			
			
			return basketDao.getMyAddresslist(u_id);
		}

		public int saveAddress(String u_id, AddressDto addressDto) {
			log.info("saveAddress");
			
			 Map<String, Object> addressConfirmMap = new HashMap<>();
			 addressConfirmMap.put("u_id", u_id);
			 addressConfirmMap.put("addressDto", addressDto);
			 
			 basketDao.saveAddress(addressConfirmMap);
			 
			 int da_no = ((BigInteger) addressConfirmMap.get("da_no")).intValue();
			
			return da_no;
		}

		public Map<String, List<SalesDto>> getMySearchList(String u_id, String s_reg_date) {
			log.info("getMySearchList");
			
			 Map<String, Object> searchMap = new HashMap<>();
			 searchMap.put("u_id", u_id);
			 searchMap.put("s_reg_date", s_reg_date);
			
			List<SalesDto> salesDtos = basketDao.getMySearchList(searchMap);
			
			 Map<String, List<SalesDto>> searchOrderGroupByDatetime = salesDtos.stream()
			            .collect(Collectors.groupingBy(SalesDto::getS_reg_date));
		
			 //내림차순
			 Map<String, List<SalesDto>> sortedsearchOrderGroupByDatetime = new TreeMap<>(Comparator.reverseOrder());
			 sortedsearchOrderGroupByDatetime.putAll(searchOrderGroupByDatetime);
			
			
			return sortedsearchOrderGroupByDatetime;
		}




//	public Map<Integer, GoodsDto> basketMap(List<Integer> gNo) {
//		log.info("basketMap");
//		
//		   Map<Integer, GoodsDto> baksetgoodsMap = new HashMap<>();
//		   
//		   for (int g_no : gNo) {
//			   
//			   GoodsDto goodsDto = goodsService.goodsDetailView(g_no);
//
//			   int quantity = basketDao.getQuantity(g_no);
//			   
//			   goodsDto.setS_quantity(quantity);
//			   baksetgoodsMap.put(g_no, goodsDto);
//			 }
//		   
//		   
//		   return baksetgoodsMap;
//		
//		
//	}



}
