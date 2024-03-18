package com.dogdam.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dogdam.shop.admin.AdminConfig;
import com.dogdam.shop.admin.goods.GoodsDto;
import com.dogdam.shop.user.donation.DonationService;
import com.dogdam.shop.user.goods.UserGoodsService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {
	
	@Autowired
	AdminConfig adminConfig;
	
	@Autowired
	UserGoodsService userGoodsService;
	
	@Autowired
	DonationService donationService;
	
	@GetMapping({"", "/"})
	public String home(Model model) {
		log.info("home()");
		
		String nextPage = "home";
		
		//도네이션 메인
		int allDonationTotalAmount = donationService.allDonationAmount();
		model.addAttribute("allDonationTotalAmount", allDonationTotalAmount);
		
		
		List<GoodsDto> goodsDetailView = userGoodsService.userGoodsList();
		model.addAttribute("goodsDetailView", goodsDetailView);
		
		List<GoodsDto> orderByRegDate = userGoodsService.orderByRegDate();
		List<GoodsDto> orderByBookmark = userGoodsService.orderByBookmark();
		List<GoodsDto> orderByBuy = userGoodsService.orderByBuy();
		/* List<GoodsDto> orderByRegDate = userGoodsService.orderByRegDate(); */
		
		model.addAttribute("orderByRegDate", orderByRegDate);
		model.addAttribute("orderByBookmark", orderByBookmark);
		model.addAttribute("orderByBuy", orderByBuy);
		
		model.addAttribute("feedCNo", 1);
		model.addAttribute("snackCNo", 2);
		model.addAttribute("beautyCNo", 3);
		model.addAttribute("clothesCNo", 4);
		model.addAttribute("toyCNo", 5);
		
		return nextPage;
	}
	

	@GetMapping("/admin")
	public String adminHome(Model model) {
		log.info("adminHome()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		String nextPage = AdminConfig.generateViewPath(AdminConfig.LOGIN_FORM);
		
		return nextPage;
		
	}
}

// task 001
// task 002
// task 003

// kms test
