package com.dogdam.shop.user.goods;

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
import com.dogdam.shop.user.bookmark.BookmarkService;
import com.dogdam.shop.user.member.MemberDto;
import com.dogdam.shop.user.qa.QnaDto;
import com.dogdam.shop.user.qa.QnaService;
import com.dogdam.shop.user.review.ReviewDto;
import com.dogdam.shop.user.review.ReviewService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
@RequestMapping("/user/goods")
public class UserGoodsController {
	
	@Autowired
	UserGoodsService goodsService;
		
	@Autowired
	ReviewService reviewService;
	
	/* 상품 문의 부분 */	
	@Autowired
	QnaService qnaService;
	
	/* 북마크 부분 */	
	@Autowired
	BookmarkService bookmarkService;
	
	// 상품 리스트 조회
		@GetMapping("/goodsList")
		public String getMethodName(HttpSession session, Model model) {
			log.info("userGoodsList");
			
			String nextPage = "user/goods/goods_list";
			
			List<GoodsDto> goodsDtos = goodsService.userGoodsList();
			List<ReviewDto> reviewDtos = reviewService.seletStar();
			
			log.info("user goodsDtos >>>>>>>>>> " + goodsDtos);
			log.info("user reviewDtos >>>>>>>>>> " + reviewDtos);
			
			model.addAttribute("goodsDtos", goodsDtos);
			model.addAttribute("reviewDtos", reviewDtos);
			
			return nextPage;
		}
	
	
	// 상품 상세 화면 & 리뷰
	@GetMapping("/goodsDetailView")
	public String goodsDetailView(@RequestParam("g_no") int g_no, HttpSession session, Model model) {
		log.info("goodsDetailView()");

		String nextPage = "user/goods/goods_detail_view";
		
		GoodsDto goodsDto = goodsService.userGoodsDetatilView(g_no);
		List<ReviewDto> reviewDtos = reviewService.selectList(g_no);
		
		/* 상품 문의 부분 */
		List<QnaDto> qnaDtos = qnaService.qnaListforGoods(g_no);
		
		/* 북마크 부분 >>> */
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");	
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
		boolean isBookmarked = bookmarkService.bookmarkHeart(g_no, loginedMemberDto.getU_id());
		/* <<< 북마크 부분 */
		
		model.addAttribute("goodsDto", goodsDto);
		model.addAttribute("reviewDtos", reviewDtos);
		
		/* 상품 문의 부분 */
		model.addAttribute("qnaDtos", qnaDtos);
		
		/* 북마크 부분 >>> */
		model.addAttribute("isBookmarked", isBookmarked);
		/* <<< 북마크 부분 */
		
		log.info("g_no>>>>>>>>>>>>>>>>>>>>>>" + g_no);
		
		return nextPage;
	}
	

	// 검색
	@GetMapping("/searchGoodsConfirm")
	public String searchGoodsConfirm(GoodsDto goodsDto, Model model) {
		log.info("searchBookConfirm()");
		
		String nextPage = "user/goods/search_goods";
		
		List<GoodsDto> goodsDtos = goodsService.searchGoodsConfirm(goodsDto);
		
		model.addAttribute("goodsDtos", goodsDtos);
		
		return nextPage;
		
	}
	
	@GetMapping("/searchCategory")
	public String searchFeed(GoodsDto goodsDto, @RequestParam("c_no") int c_no, Model model) {
		log.info("searchCategory()");
		
		String nextPage = "user/goods/search_category_form";
		
		List<GoodsDto> goodsDtos = goodsService.searchCategory(c_no);
		if (!goodsDtos.isEmpty()) {
	        model.addAttribute("goodsDto", goodsDtos.get(0)); // 첫 번째 요소만 모델에 추가
	    }
		model.addAttribute("goodsDtos", goodsDtos);
		
		return nextPage;
	}
		
	@PostMapping("/searchProductsByCategory")
	@ResponseBody
	public List<GoodsDto> searchProductsByCategory(@RequestParam("c_no") int c_no) {
	    // c_no를 기반으로 상품을 검색하고 리스트를 반환하는 로직을 구현해야 합니다.
	    List<GoodsDto> GoodsDto = goodsService.searchProductsByCategory(c_no);
	    
	    return GoodsDto;
	}

}
