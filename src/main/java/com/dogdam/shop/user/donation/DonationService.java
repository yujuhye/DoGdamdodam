package com.dogdam.shop.user.donation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DonationService {

	@Autowired
	IDonationDaoForMybatis donationDao;

	public List<DonationDto> getmydonationList(String u_id) {
		log.info("getmydonationList");
		
		
		return donationDao.getmydonationList(u_id);
	}

	public int mydonationTotal(List<DonationDto> donationDtos) {
		log.info("mydonationTotal");
		
		int donationTotalAmount = 0;
		
		for(int i =0; i < donationDtos.size(); i++) {
			
			donationTotalAmount += donationDtos.get(i).getD_donation();
			
		}
		return donationTotalAmount;
	}

	public int allDonationAmount() {
		log.info("allDonationAmount");
		
		List<DonationDto> allDonationDtos = donationDao.getAllDonationList();
		
		int allDonationTotalAmount = 0;
		
		for(int i = 0; i < allDonationDtos.size(); i++ ) {
			
			allDonationTotalAmount += allDonationDtos.get(i).getD_donation();
			
		}
		return allDonationTotalAmount;
	}

	public List<DonationDto> donationRanking() {
		log.info("donationRanking");
		
		List<DonationDto> rankingDonationDtos =  donationDao.getdonationRanking();
		
		return rankingDonationDtos;
		
	}
	
}
