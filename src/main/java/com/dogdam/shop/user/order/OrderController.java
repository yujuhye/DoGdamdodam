package com.dogdam.shop.user.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogdam.shop.admin.goods.GoodsDto;
import com.dogdam.shop.user.member.MemberDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/order_form")
	public String orderForm(@RequestParam("g_no") int g_no, 
			@RequestParam("s_quantity") int s_quantity, Model model, HttpSession session ) {
		log.info("orderForm");
		
		String nextPage = "user/order/order_details";
		
		MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		
		 if (loginedMemberDto == null) {
			 loginedMemberDto = new MemberDto();
			 nextPage =  "redirect:/user/member/login_form";
		    }
		
		AddressDto addressDto = orderService.getAddressInfo(loginedMemberDto.getU_id());

		model.addAttribute("addressDto", addressDto);
		
		GoodsDto goodsDto = orderService.getGoods(g_no);
		model.addAttribute("goodsDto", goodsDto);
		
		session.setAttribute("s_quantity", s_quantity);
		
		int donationAmount = orderService.calculateDonation(g_no, s_quantity);
		int orderAmount = orderService.calculateorderAmount(g_no, s_quantity);
		
		model.addAttribute("donationAmount", donationAmount); 
		model.addAttribute("orderAmount", orderAmount);
		
		        
		return nextPage;
		
	}
	
	@PostMapping("/order_confirm")
	public String orderConfirm(HttpSession session,  
			   @RequestParam("g_no") int g_no, 
               @RequestParam("s_quantity") int s_quantity,
               @RequestParam(value = "da_no", required = false) Integer da_no, AddressDto addressDto) {
		log.info("orderConfirm");
		
		String nextPage="user/order/order_success";
		
		MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		String u_id = loginedMemberDto.getU_id();
		
		int result = -1;
		
		if(da_no != null) {
			
			result = orderService.orderConfirm(s_quantity, g_no, u_id, da_no);
			
		} else {
			
			AddressDto selectedAddressDto = orderService.newAddressConfirm(addressDto, u_id);
			result = orderService.orderConfirm(s_quantity, g_no, u_id, selectedAddressDto.getDa_no());
		}
		
		
		if (result > 0) {
			orderService.donationConfirm(g_no, s_quantity, u_id);
			
		} else {
			nextPage="user/order/order_fail";
		}
		
		return nextPage;
	}
	
	//ajax
	
	@PostMapping("/addresslist")
	@ResponseBody
	public Object addresslist(HttpSession session) {
		log.info("addresslist");
		
		MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		String u_id = loginedMemberDto.getU_id();
		
		List<AddressDto> addressDtos = orderService.addresslist(u_id);
		
		return addressDtos;
	}

	@PostMapping("/writeAddress")
	@ResponseBody
	public Object writeAddress(AddressDto addressDto, HttpSession session) {
		log.info("writeAddress");
		
		MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		String u_id = loginedMemberDto.getU_id();

		
		orderService.writeAddress(addressDto, u_id);
		
		return null;
	}

	@GetMapping("/sendSelectedAddress")
	@ResponseBody
	public Object sendSelectedAddress(@RequestParam("da_no") int da_no) {
		
		AddressDto addressDto = orderService.sendSelectedAddress(da_no);
		log.info("addressDto>>>>>>>>>>>>>>>>" , addressDto.getDa_no());
		
		
		return addressDto;
	}

}
