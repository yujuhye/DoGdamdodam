package com.dogdam.shop.user.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ReviewService {
	
	@Autowired
	IReviewDaoMybatis reviewDao;
	
	final static public int REVIEW_INSERT_SUCCESS = 1;
	final static public int REVIEW_INSERT_FAIL = 0;

	public List<ReviewDto> selectList(int g_no) {
		log.info("selectList()");
		
		return reviewDao.selectList(g_no);
	}

	public int insertReview(ReviewDto reviewDto) {
		log.info("insertReview()");
		
		int result = reviewDao.insertReview(reviewDto);
		
		if(result > 0)
			return REVIEW_INSERT_SUCCESS;
		else 
			return REVIEW_INSERT_FAIL;
		
	}

	public ReviewDto reviewDetailView(int r_no) {
		log.info("reviewDetailView()");
		
		return reviewDao.reviewDetailView(r_no);
	}
	
	public ReviewDto modifyReviewFrom(int r_no) {
		log.info("modifyReviewFrom()");
		return reviewDao.selectReview(r_no);
	}


	public ReviewDto selectModifyReview(int r_no) {
		log.info("selectModifyReview()");
		
		return reviewDao.selectModifyReview(r_no);
	}
	
	public int modifyReview(ReviewDto reviewDto) {
		log.info("modifyReview()");
		return reviewDao.modifyReviewConfirm(reviewDto);
	}

	public int reviewDelete(int r_no) {
		log.info("reviewDelete()");
		return reviewDao.reviewDeleteConfirm(r_no);
	}

	// 본인이 작성한 리뷰만 확인하기
	public List<ReviewDto> reviewAllSelect(String u_id) {
		log.info("reviewAllSelect()");
		
		return reviewDao.reviewAllSelect(u_id);
	}

	// 별점 관련
	public List<ReviewDto> seletStar() {
		log.info("seletStar()");
		
		return reviewDao.seletStar();
	}

	public List<ReviewDto> userReviewAllSelect(String u_id) {
		log.info("userReviewAllSelect()");
		
		return reviewDao.userReviewAllSelect(u_id);
	}


}
