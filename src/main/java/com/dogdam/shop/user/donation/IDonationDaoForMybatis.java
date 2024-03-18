package com.dogdam.shop.user.donation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IDonationDaoForMybatis {

	List<DonationDto> getmydonationList(String u_id);

	List<DonationDto> getAllDonationList();

	List<DonationDto> getdonationRanking();

}
