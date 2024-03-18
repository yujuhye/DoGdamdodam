package com.dogdam.shop.user.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogdam.shop.admin.goods.GoodsDto;
import com.dogdam.shop.admin.goods.GoodsService;
import com.dogdam.shop.user.goods.UserGoodsService;
import com.dogdam.shop.user.member.MemberDto;
import com.dogdam.shop.util.UploadFileService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	@Autowired
	UploadFileService uploadFileService;

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	UserGoodsService userGoodsService;

	@Autowired
	RestTemplate restTemplate;

	/*
	 * 리뷰 리스트 조회
	 */
	@GetMapping("/reviewList")
	public Object reviewList(HttpSession session, Model model, @RequestParam("u_id") String u_id) {
		log.info("goodsList()");

		String nextPage = "review/review_list";

		List<ReviewDto> reviewDtos = reviewService.reviewAllSelect(u_id);

		log.info("reviewDtos >>>>>>>>> " + reviewDtos);

		model.addAttribute("reviewDtos", reviewDtos);

		return nextPage;
	}

	/*
	 * 내가 남긴 리뷰만 확인
	 */
	/*
	 * @GetMapping("/userReviewList") public String userReviewList(HttpSession
	 * session, Model model, @RequestParam("u_id") String
	 * u_id, @RequestParam(value="r_no") int r_no) { log.info("userReviewList");
	 * 
	 * String nextPage = "review/user_review_list";
	 * 
	 * MemberDto loginedMemberDto = (MemberDto)
	 * session.getAttribute("loginedMemberDto"); if (loginedMemberDto == null) {
	 * loginedMemberDto = new MemberDto(); return
	 * "redirect:/user/member/login_form"; }
	 * 
	 * log.info("review 내가 남긴 리뷰 확인 >>>>>>>>>> "+ r_no);
	 * 
	 * List<ReviewDto> reviewDtos =
	 * reviewService.userReviewAllSelect(loginedMemberDto.getU_id()); List<GoodsDto>
	 * goodsDtos = userGoodsService.selectListByGNo(r_no);
	 * 
	 * model.addAttribute("reviewDtos", reviewDtos); model.addAttribute("goodsDtos",
	 * goodsDtos);
	 * 
	 * log.info("userReviewAllSelect() >>>>>>>>> " + reviewDtos);
	 * 
	 * return nextPage; }
	 */
	@GetMapping("/userReviewList")
	public String userReviewList(HttpSession session, Model model) {
	    // 로그인된 사용자 정보 가져오기
	    MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");
	    if (loginedMemberDto == null) {
	        // 사용자가 로그인하지 않았다면 로그인 페이지로 리다이렉트
	        return "redirect:/user/member/login_form";
	    }

	    // 로그인한 사용자 ID로 리뷰 목록 조회
	    List<ReviewDto> reviewDtos = reviewService.userReviewAllSelect(loginedMemberDto.getU_id());
	    // 해당 리뷰들에 대한 상품 정보를 저장할 리스트 초기화
	    List<GoodsDto> goodsDtos = new ArrayList<>();

	    // 각 리뷰에 대해 해당하는 상품 정보 조회 및 리스트에 추가
	    for (ReviewDto review : reviewDtos) {
	        GoodsDto goodsDto = userGoodsService.selectByGNo(review.getG_no()); // 상품 정보 조회
	        goodsDtos.add(goodsDto); // 조회된 상품 정보를 리스트에 추가
	    }

	    // 모델에 리뷰 목록과 상품 정보 목록 추가
	    model.addAttribute("reviewDtos", reviewDtos);
	    model.addAttribute("goodsDtos", goodsDtos);

	    // 뷰 이름 반환
	    return "review/user_review_list";
	}
	
	
	/*
	 * 리뷰 등록 폼 이동
	 */
	@GetMapping("/reviewInsertForm")
	public String reviewInsertForm(@RequestParam("g_no") int g_no, Model model, HttpSession session) {
		log.info("reviewInsertForm()");

		String nextPage = "review/review_insert_form";

		MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");
		if (loginedMemberDto == null) {
			loginedMemberDto = new MemberDto();
			return "redirect:/user/member/login_form";
		}

		log.info("리뷰 남기기 g_no >>>>>>>>>> " + g_no);
		model.addAttribute("g_no", g_no);
		
		return nextPage;
	}
	
	/*
	 * @GetMapping("/reviewInsertForm") public String reviewInsertForm(HttpSession
	 * session, @RequestParam("r_no") int r_no, Model model) {
	 * log.info("reviewInsertForm()");
	 * 
	 * String nextPage = "review/review_insert_form";
	 * 
	 * MemberDto loginedMemberDto = (MemberDto)
	 * session.getAttribute("loginedMemberDto"); if (loginedMemberDto == null) {
	 * loginedMemberDto = new MemberDto(); return "rediret:/user/member/login_form";
	 * }
	 * 
	 * ReviewDto reviewDto = reviewService.selectModifyReview(r_no);
	 * model.addAttribute("reviewDto", reviewDto);
	 * 
	 * return nextPage; }
	 */

	/*
	 * 리뷰 등록
	 */
	@PostMapping("/insertReviewConfirm")
	@ResponseBody
	public Object insertReviewConfirm(ReviewDto reviewDto, HttpSession session,
			@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
			@RequestParam("file3") MultipartFile file3, @RequestParam("file4") MultipartFile file4,
			@RequestParam("u_id") String u_id, @RequestParam("g_no") int g_no) throws Exception {

		log.info("insertReviewConfirm()");

		MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");
		if (loginedMemberDto == null)
			return "redirect:/user/member/login_form";

		log.info("u_id >>>>>>>>>> " + loginedMemberDto.getU_id());
		log.info("g_no >>>>>>>>>> " + g_no);

		String nextPage = "redirect:/admin/goods/userReviewList";

		// request header 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		// request body 설정
		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("file1", file1.getResource());
		requestBody.add("file2", file2.getResource());
		requestBody.add("file3", file3.getResource());
		requestBody.add("file4", file4.getResource());

		// request entity
		// 응답 받는 객체 -> 바디와 헤더에 대해 응답 받는 거
		HttpEntity<MultiValueMap<String, Object>> responseEntity = new HttpEntity<>(requestBody, headers);

		// api 호출
		// 실제 이미지 서버 주소
		// 실제 이미지가 저장될 타겟 주소를 넣어주면 돼
		// 해당 커맨드를 서버에서 받아주면 돼
		String serverURL = "http://14.42.124.95:8091/upload_file";

		// 이쪽 주소로 파일이 날아가
		ResponseEntity<Object> response = restTemplate.postForEntity(serverURL, responseEntity, Object.class);

		Map<String, String> map = (Map<String, String>) response.getBody();
		String savedFileName1 = map.get("savedFileName1"); // 새파일명 or null
		String savedFileName2 = map.get("savedFileName2"); // 새파일명 or null
		String savedFileName3 = map.get("savedFileName3"); // 새파일명 or null
		String savedFileName4 = map.get("savedFileName4"); // 새파일명 or null

		if (map != null) {

			reviewDto.setR_thumbnail_name(savedFileName1);
			reviewDto.setR_second_name(savedFileName2);
			reviewDto.setR_third_name(savedFileName3);
			reviewDto.setR_last_name(savedFileName4);

			reviewDto.setU_id(loginedMemberDto.getU_id());
			reviewDto.setG_no(g_no);

			int result = reviewService.insertReview(reviewDto);

			log.info(reviewDto.getR_thumbnail_name());

			if (result <= 0) {

				nextPage = "redirect:/review/userReviewList";
			}

		}

		return nextPage;
	}

	/*
	 * 리뷰 상세화면
	 */
	@GetMapping("/reviewDetailView")
	public String reviewDetailView(@RequestParam("r_no") int r_no, Model model) throws Exception {
		log.info("reviewDetailView()");

		String nextPage = "review/review_detail_view";

		ReviewDto reviewDto = reviewService.reviewDetailView(r_no);
		model.addAttribute("reviewDto", reviewDto);

		return nextPage;

	}

	/*
	 * 리뷰 수정폼 이동 -- 여기 수정부터 다시하자
	 */
	@GetMapping("/reviewModifyForm")
	public String reviewModifyForm(@RequestParam("r_no") int r_no, Model model, HttpSession session) {
		log.info("reviewModifyForm()");

		String nextPage = "review/review_modify_form";

		MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");
		if (loginedMemberDto == null) {
			loginedMemberDto = new MemberDto();
			return "redirect:/user/member/login_form";
		}

		ReviewDto reviewDto = reviewService.selectModifyReview(r_no);
		model.addAttribute("reviewDto", reviewDto);

		log.info("modifyReview() : g_no >>>>>>>>>>>>>> " + r_no);

		return nextPage;
	}

	/*
	 * 리뷰 수정
	 * 
	 * @PostMapping("/reviewModifyConfirm") public Object
	 * reviewModifyConfirm(ReviewDto reviewDto, HttpSession session,
	 * 
	 * @RequestParam("file1") MultipartFile file1, @RequestParam("file2")
	 * MultipartFile file2,
	 * 
	 * @RequestParam("file3") MultipartFile file3, @RequestParam("file4")
	 * MultipartFile file4,
	 * 
	 * @RequestParam("u_id") String u_id, @RequestParam("g_no") int g_no) throws
	 * Exception {
	 * 
	 * log.info("reviewModifyConfirm()");
	 * 
	 * String nextPage = "review/review_modify_success";
	 * 
	 * MemberDto loginedMemberDto = (MemberDto)
	 * session.getAttribute("loginedMemberDto");
	 * 
	 * // request header 설정 HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	 * 
	 * // request body 설정 MultiValueMap<String, Object> requestBody = new
	 * LinkedMultiValueMap<>(); requestBody.add("file1", file1.getResource());
	 * requestBody.add("file2", file2.getResource()); requestBody.add("file3",
	 * file3.getResource()); requestBody.add("file4", file4.getResource());
	 * 
	 * // request entity // 응답 받는 객체 -> 바디와 헤더에 대해 응답 받는 거
	 * HttpEntity<MultiValueMap<String, Object>> responseEntity = new
	 * HttpEntity<>(requestBody, headers);
	 * 
	 * // api 호출 // 실제 이미지 서버 주소 // 실제 이미지가 저장될 타겟 주소를 넣어주면 돼 // 해당 커맨드를 서버에서 받아주면 돼
	 * String serverURL = "http://14.42.124.95:8091/upload_file";
	 * 
	 * // 이쪽 주소로 파일이 날아가 ResponseEntity<Object> response =
	 * restTemplate.postForEntity(serverURL, responseEntity, Object.class);
	 * 
	 * Map<String, String> map = (Map<String, String>) response.getBody(); String
	 * savedFileName1 = map.get("savedFileName1"); // 새파일명 or null String
	 * savedFileName2 = map.get("savedFileName2"); // 새파일명 or null String
	 * savedFileName3 = map.get("savedFileName3"); // 새파일명 or null String
	 * savedFileName4 = map.get("savedFileName4"); // 새파일명 or null
	 * 
	 * if (map != null) {
	 * 
	 * reviewDto.setR_thumbnail_name(savedFileName1);
	 * reviewDto.setR_second_name(savedFileName2);
	 * reviewDto.setR_third_name(savedFileName3);
	 * reviewDto.setR_last_name(savedFileName4);
	 * 
	 * reviewDto.setU_id(loginedMemberDto.getU_id()); reviewDto.setG_no(g_no);
	 * 
	 * int result = reviewService.modifyReview(reviewDto);
	 * 
	 * log.info(reviewDto.getR_thumbnail_name());
	 * 
	 * if (result <= 0) {
	 * 
	 * nextPage = "review/review_modify_fail"; }
	 * 
	 * }
	 * 
	 * return nextPage; }
	 */

	/*
	 * 리뷰 수정
	 */
	@PostMapping("/reviewModifyConfirm")
	@ResponseBody
	public Object reviewModifyConfirm(ReviewDto reviewDto, HttpSession session,
			@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
			@RequestParam("file3") MultipartFile file3, @RequestParam("file4") MultipartFile file4,
			@RequestParam("u_id") String u_id, @RequestParam("g_no") int g_no) throws Exception {

		log.info("reviewModifyConfirm()");

		int result = -1;
		
		MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");

		// request header 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		// request body 설정
		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("file1", file1.getResource());
		requestBody.add("file2", file2.getResource());
		requestBody.add("file3", file3.getResource());
		requestBody.add("file4", file4.getResource());

		// request entity
		// 응답 받는 객체 -> 바디와 헤더에 대해 응답 받는 거
		HttpEntity<MultiValueMap<String, Object>> responseEntity = new HttpEntity<>(requestBody, headers);

		// api 호출
		// 실제 이미지 서버 주소
		// 실제 이미지가 저장될 타겟 주소를 넣어주면 돼
		// 해당 커맨드를 서버에서 받아주면 돼
		String serverURL = "http://14.42.124.95:8091/upload_file";

		// 이쪽 주소로 파일이 날아가
		ResponseEntity<Object> response = restTemplate.postForEntity(serverURL, responseEntity, Object.class);

		Map<String, String> map = (Map<String, String>) response.getBody();
		String savedFileName1 = map.get("savedFileName1"); // 새파일명 or null
		String savedFileName2 = map.get("savedFileName2"); // 새파일명 or null
		String savedFileName3 = map.get("savedFileName3"); // 새파일명 or null
		String savedFileName4 = map.get("savedFileName4"); // 새파일명 or null

		if (map != null) {
			if (!file1.isEmpty()) {

				reviewDto.setR_thumbnail_name(savedFileName1);
				log.info("file1" + file1);

			}

			if (!file2.isEmpty()) {

				reviewDto.setR_second_name(savedFileName2);
				log.info("file2" + file2);

			}

			if (!file3.isEmpty()) {

				reviewDto.setR_third_name(savedFileName3);
				log.info("file3" + file3);

			}

			if (!file4.isEmpty()) {

				reviewDto.setR_last_name(savedFileName4);
				log.info("file4" + file4);

			}

			reviewDto.setU_id(loginedMemberDto.getU_id());
			reviewDto.setG_no(g_no);

			result = reviewService.modifyReview(reviewDto);

			log.info(reviewDto.getR_thumbnail_name());

			
		}

		return new ResponseEntity<>(Map.of("success", true, "message", "리뷰 수정이 완료되었습니다."), HttpStatus.OK);
	}

	/*
	 * 리뷰 삭제
	 */
	@GetMapping("/reviewDelete")
	public String reviewDelete(@RequestParam("r_no") int r_no, HttpSession session, RedirectAttributes redirectAttributes) {
	    log.info("reviewDelete()");

	    MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");
	    if (loginedMemberDto == null) {
	        return "redirect:/admin/goods/userReviewList";
	    }

	    int result = reviewService.reviewDelete(r_no);
	    
	    if (result <= 0) {
	        // 에러 처리를 위한 메시지 추가 등을 여기서 할 수 있습니다.
	        // 예: redirectAttributes.addFlashAttribute("errorMessage", "리뷰 삭제 실패");
	        return "redirect:/review/userReviewList";
	    }

	    return "redirect:/review/userReviewList";
	}
	

}
