package com.dogdam.shop.user.review;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IReviewDaoMybatis {

	public List<ReviewDto> selectList(int g_no);

	public int insertReview(ReviewDto reviewDto);

	public ReviewDto reviewDetailView(int r_no);

	public ReviewDto selectReview(int r_no);

	public int modifyReviewConfirm(ReviewDto reviewDto);

	public int reviewDeleteConfirm(int r_no);

	// 본인이 작성한 리뷰만 확인하기(my page)
	public List<ReviewDto> reviewAllSelect(String u_id);

	public List<ReviewDto> seletStar();

	public List<ReviewDto> userReviewAllSelect(String u_id);
	// 수정 정보 불러오기
	public ReviewDto selectModifyReview(int r_no);

	public int checkReviewStatus(Map<String, Object> map);



}
