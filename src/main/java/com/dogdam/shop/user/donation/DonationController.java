package com.dogdam.shop.user.donation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogdam.shop.user.member.MemberDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/donation")
public class DonationController {
	
	@Autowired
	DonationService donationService;

	@GetMapping("/mydonationList")
	public String  mydonationList(HttpSession session, Model model) {
		log.info("mydonationList");
		
		String nextPage="user/donation/mydonationList";
		
		MemberDto loginedMemberDto =(MemberDto) session.getAttribute("loginedMemberDto");
		String u_id = loginedMemberDto.getU_id();
		
		List<DonationDto> donationDtos = donationService.getmydonationList(u_id);
		model.addAttribute("donationDtos", donationDtos);
		
		int donationTotalAmount = donationService.mydonationTotal(donationDtos);
		model.addAttribute("donationTotalAmount", donationTotalAmount);
		
		return nextPage;
	}
	
	
	@GetMapping("/donation_ranking")
	public String donationRanking(Model model) {
		log.info("donationRanking");
		
		String nextPage="user/donation/donation_ranking";
		
		List<DonationDto> rankingDonationDtos = donationService.donationRanking();
		
		model.addAttribute("rankingDonationDtos", rankingDonationDtos);
		
		return nextPage;
	}
}
