package com.dogdam.shop.user.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogdam.shop.admin.goods.GoodsDto;
import com.dogdam.shop.admin.goods.GoodsService;
import com.dogdam.shop.user.member.MemberDto;
import com.dogdam.shop.user.order.AddressDto;
import com.dogdam.shop.user.review.ReviewDto;
import com.dogdam.shop.user.review.ReviewService;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/basket")
public class BasketController {
	

	@Autowired 
	BasketService basketService;
	@Autowired
	GoodsService goodsService;
	
	/*0319 add : review*/
	@Autowired
	ReviewService reviewService;

	
	   @PostMapping("/basketConfirm")
	   @ResponseBody
	   public Object  basketConfirm(@RequestParam("g_no") int g_no, 
			 @RequestParam("s_quantity") int s_quantity,  
	         HttpSession session, Model model) {
	      log.info("basketConfirm");
	      
	      
	      MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
	      
	      
	       if (loginedMemberDto == null) {
	          loginedMemberDto = new MemberDto();
	          return "redirect:/user/member/login_form";
	          }
	      
	      String u_id = loginedMemberDto.getU_id();
	      
	      Map<String, Object> basketMap = new HashMap<>();
	      basketMap.put("u_id", u_id);
	      basketMap.put("s_quantity", s_quantity);
	      basketMap.put("g_no", g_no);
	      
	      
	      int response = basketService.basketConfirm(basketMap); 
	      
	      return response;
	   }
	   
	   @GetMapping("/basketList")
	   public String basketList(HttpSession session, Model model) {
	      log.info("basketList");
	      
	      String nextPage="user/basket/basketList";
	      
	      MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
	      
//	       if (loginedMemberDto == null) {
//	          loginedMemberDto = new MemberDto();
//	            nextPage =  "redirect:/user/member/login_form";
//	          
//	          }
	      
	      List<BasketDto> basketDtos = basketService.getAllBasketList(loginedMemberDto.getU_id());
	      
	      model.addAttribute("basketDtos", basketDtos);
	      
	      return nextPage;
	      
	   }
	   
	   @PostMapping("/basketDelete")
	   @ResponseBody
	   public int basketDelete(@RequestParam("g_no") int g_no) {
	      log.info("basketDelete");
	      
	       basketService.basketDelete(g_no);
	      
	      return  basketService.basketDelete(g_no);
	   }
	   
	   @PostMapping("/basketMap")
	   public String basketMap(@RequestParam( value="g_no", required = false) List<Integer> gNo, 
			   Model model, HttpSession session,  @RequestParam(value="s_quantity", defaultValue = "-1") int sQuantity) {
		   log.info("basketMap");
		      
		   String nextPage = "user/basket/basket_order_details";
		   
		   MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		  
			
			 if (loginedMemberDto == null) {
				 loginedMemberDto = new MemberDto();
				 nextPage =  "redirect:/user/member/login_form";
			    }
			
			AddressDto addressDto = basketService.getAddressInfoById(loginedMemberDto.getU_id());

			model.addAttribute("addressDto", addressDto);
			
//			Map<Integer, GoodsDto> baksetgoodsMap = basketService.basketMap(gNo);
			
		   Map<Integer, GoodsDto> baksetgoodsMap = new HashMap<>();
		   
		   for (int g_no : gNo) {
			   
			   GoodsDto goodsDto = goodsService.goodsDetailView(g_no);
			   
			   String u_id = loginedMemberDto.getU_id();

			   int quantity = (sQuantity != -1) ? sQuantity : basketService.getQuantity(g_no, u_id);
			   
			   goodsDto.setS_quantity(quantity);
			   baksetgoodsMap.put(g_no, goodsDto);
			 }
		   
		   model.addAttribute("baksetgoodsMap", baksetgoodsMap);
		   
		   int baskettotalAmountExDel = basketService.basketOrderAmount(baksetgoodsMap);
		   int basketDonationAmount = basketService.basketDonationAmount(baskettotalAmountExDel);
		   int basketTotalAmount = basketService.basektOrderAmountAndDel(baskettotalAmountExDel);
		   
		   model.addAttribute("basketTotalAmount", basketTotalAmount);
		   model.addAttribute("basketDonationAmount", basketDonationAmount);
		   model.addAttribute("baskettotalAmountExDel", baskettotalAmountExDel);
		  
	      
	      return  nextPage;
		   
	   }
	   
	   @PostMapping("/basket_order_confirm")
	   public String basketOrderConfirm(@RequestParam( value="g_no", required = false) List<Integer> gNo, 
			   @RequestParam(value="origin_da_no", defaultValue = "0") Integer originDaNo, 
			   @RequestParam(value="selected_da_no", defaultValue = "-1") Integer selectedDaNo, 
			   @RequestParam( value="s_quantity", required = false) List<Integer> sQuantity, 
			    AddressDto addressDto, HttpSession session, Model model) {
		   log.info("basketOrderConfirm");
		   
		   String nextPage="user/order/order_success";
		   
		   MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		   String u_id = loginedMemberDto.getU_id();
		   
		   
		   String newOrderNumber = basketService.generateOrderNumber();
		   
		   model.addAttribute("newOrderNumber", newOrderNumber);
		   
		   int result = basketService.orderDetailsConfirm(u_id, newOrderNumber);
		   
		   int da_no = 0;
		  
		   if (selectedDaNo != -1) {
			    da_no = selectedDaNo;
			} else if (originDaNo != 0) {
			    da_no = originDaNo;
			} else if ( addressDto != null) {
			    da_no = basketService.saveNewAddress(addressDto, u_id);
			} else {
			    log.info("배송지가 없습니다");
			}
		   
//		   Map<Object, Object> salesMap = new HashMap<>();
		   List<SalesDto> salesDtoList = new ArrayList<>();
		   
		   for (int i = 0; i < gNo.size(); i++) {
		        int g_no = gNo.get(i);
		        int quantity = sQuantity.get(i); 
		        
		        SalesDto salesDto = new SalesDto();
		        salesDto.setU_id(u_id);
		        salesDto.setS_quantity(quantity); 
		        salesDto.setG_no(g_no);
		        salesDto.setDa_no(da_no);
		        salesDto.setO_no(newOrderNumber);
		        salesDtoList.add(salesDto);
		    }
//		   List<SalesDto> salesDtoList = new ArrayList<>(salesMap.values());
		   
		    result = basketService.orderConfirm(salesDtoList);
		   
		   if(result > 0) {
			   
			   Map<Integer, GoodsDto> baksetgoodsMap = new HashMap<>();
			   
			   for (int i = 0; i < gNo.size(); i++) {
			        int g_no = gNo.get(i);
			        int quantity = sQuantity.get(i); 
			        
			          basketService.deleteBasketByOrder(g_no);	
			      
					  GoodsDto goodsDto = goodsService.goodsDetailView(g_no);
					  goodsDto.setS_quantity(quantity);
					  baksetgoodsMap.put(g_no, goodsDto);
			      
			    }
			   
			   int d_donation = basketService.basketDonationAmount( basketService.basketOrderAmount(baksetgoodsMap));
			   
			   basketService.donationSave(d_donation, u_id);
			   
		   } else {
			   
			   nextPage="user/order/order_fail";
		   }
		   
		   return nextPage;
	   }
	   
	   @GetMapping("/myOrderList")
	   public String myOrderList(HttpSession session, Model model) {
		   log.info("myOrderList");
		   
		   String nextPage="user/order/myOrder_list";
		   
		   MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		   
		   if (loginedMemberDto == null) {
		          loginedMemberDto = new MemberDto();
		            nextPage =  "redirect:/user/member/login_form";
		          
		          }
		   
		   String u_id = loginedMemberDto.getU_id();
		   Map<String, List<SalesDto>> sortedOrderNumbersGroupByDatetime = basketService.getMyOrderList(u_id);
		   
		   model.addAttribute("orderNumbersGroupByDatetime", sortedOrderNumbersGroupByDatetime);
		   
		   return nextPage;
	   }
	
	   
	   @GetMapping("/isBasketGNum")
	   @ResponseBody
	   public int isBasketGNum(@RequestParam("g_no") int g_no, HttpSession session) {
		   log.info("myOrderList");
		   
		   MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		   String u_id = loginedMemberDto.getU_id();
		   
		   int result = basketService.isBasketGNum(g_no, u_id);
		   
		   return result;
	   }
	   
	   @GetMapping("/myOrderDetail")
	   public String myOrderDetail(@RequestParam("s_reg_date") String s_reg_date, Model model) {
		   log.info("myOrderDetail");
		   
		   String nextPage="user/order/myOrder_detail";
		   
		   SalesDto myOrderInfo = basketService.myOrderDetail(s_reg_date);
		   List<SalesDto> myOrderGoods = basketService.myOrderGoods(s_reg_date);
		   
		   model.addAttribute("myOrderInfo", myOrderInfo);
		   model.addAttribute("myOrderGoods", myOrderGoods);
		   
		   return nextPage;
	   }
	   
	   @GetMapping("/myAddresslist")
	   @ResponseBody
	   public List<AddressDto> myAddresslist(HttpSession session) {
		   log.info("myAddresslist");
		   
		   MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		   String u_id = loginedMemberDto.getU_id();
		   
		  List<AddressDto> addressDtos = basketService.myAddresslist(u_id);
		   
		   return addressDtos;
	   }
	   
	   @PostMapping("/saveAddress")
	   @ResponseBody
	   public int saveAddress(HttpSession session, AddressDto addressDto) {
		   log.info("saveAddress");
		   
		   MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		   String u_id = loginedMemberDto.getU_id();
		   
		  int da_no =  basketService.saveAddress(u_id, addressDto);
		   
		   return da_no;
	   }
	
}
