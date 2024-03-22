package com.dogdam.shop.admin.goods;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

import com.dogdam.shop.admin.AdminConfig;
import com.dogdam.shop.admin.category.CategoryDto;
import com.dogdam.shop.admin.category.CategoryService;
import com.dogdam.shop.admin.member.AdminMemberDto;
import com.dogdam.shop.admin.qa.AQnaService;
import com.dogdam.shop.user.qa.QnaDto;
import com.dogdam.shop.user.review.ReviewDto;
import com.dogdam.shop.user.review.ReviewService;
import com.dogdam.shop.util.UploadFileService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ReviewService reviewService;

	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AQnaService aQnaService;
	
	@Autowired
	AdminConfig adminConfig;

	/*
	 * 상품 리스트 조회
	 */
	@GetMapping("/goodsList")
	public Object goodsList(HttpSession session, Model model) {
		log.info("goodsList()");

		String nextPage = "admin/goods/goods_list";
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);

		AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
		if(adminMemberDto == null)
			return "redirect:/admin/member/login_form";
		
		List<GoodsDto> goodsDtos = goodsService.selectList();
		
		log.info("goodsDtos >>>>>>>>>>>> ", goodsDtos);

		model.addAttribute("goodsDtos", goodsDtos);

		return nextPage;
	}

	/*
	 * 상품 등록
	 */
	@GetMapping("/insertGoodsForm")
	public String insertGoods(HttpSession session, Model model) {
		log.info("insertGoods()");

		String nextPage = "admin/goods/insert_goods_form";
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
		if(adminMemberDto == null)
			return "redirect:/admin/member/login_form";

		return nextPage;
	}
	
	// 대분류
	@GetMapping("/getPrimaryCategories")
	public ResponseEntity<List<CategoryDto>> getPrimaryCategories() {
	    log.info("getPrimaryCategories()");

	    List<CategoryDto> categories = categoryService.getPrimaryCategories();
	    return ResponseEntity.ok(categories);
	}

    // 중분류 목록 요청 처리
	@PostMapping("/getSecondaryCategories")
	@ResponseBody
	public List<CategoryDto> getSecondaryCategories(@RequestParam(value = "c_no2") int c_no) {
	    log.info("getSecondaryCategories()");
	    log.info("cNo >>>>>>>>>>" + c_no);

	    return categoryService.getSecondaryCategories(c_no);
	}
		
	/*
	 * 상품 등록 confirm
	 */
	@PostMapping("/insertGoodsConfirm")
	@ResponseBody
	public String insertGoodsConfirm(GoodsDto goodsDto, HttpSession session, Model model,
									@RequestParam("file1") MultipartFile file1,
									@RequestParam("file2") MultipartFile file2, 
									@RequestParam("file3") MultipartFile file3,
									@RequestParam("file4") MultipartFile file4,
									@RequestParam(value = "c_no2") int c_no) throws Exception {

		log.info("insertGoodsConfirm()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		// 관리자 session
		//AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

		String nextPage = "admin/goods/goods_insert_success";
		
		AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
		if(adminMemberDto == null)
			return "redirect:/admin/member/login_form";

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
		String savedFileName1 = map.get("savedFileName1");		// 새파일명 or null
		String savedFileName2 = map.get("savedFileName2");		// 새파일명 or null
		String savedFileName3 = map.get("savedFileName3");		// 새파일명 or null
		String savedFileName4 = map.get("savedFileName4");		// 새파일명 or null
		
		if (map != null) {
			
			goodsDto.setG_thumbnail_name(savedFileName1);
			goodsDto.setG_second_img_name(savedFileName2);
			goodsDto.setG_third_img_name(savedFileName3);
			goodsDto.setG_detail_content(savedFileName4);
			
			int result = goodsService.insertGoods(goodsDto, c_no);
			
			log.info(goodsDto.getG_thumbnail_name());

			if (result <= 0) {

				nextPage = "admin/goods/goods_insert_fail";
			}

		}

		return nextPage;
	}

	/*
	 * 상품 상세 화면
	 */
	@GetMapping("/goodsDetailView")
	public String goodsDetailView(@RequestParam("g_no") int g_no, Model model, HttpSession session) {
		log.info("goodsDetailView()");

		String nextPage = "admin/goods/goods_detail_view";
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		GoodsDto goodsDto = goodsService.goodsDetailView(g_no);
		List<ReviewDto> reviewDtos = reviewService.selectList(g_no);

		/* 상품 문의 부분 */
		List<QnaDto> aQnaDtos = aQnaService.aQnaListforGoods(g_no);
		AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
		if(adminMemberDto == null)
			return "redirect:/admin/member/login_form";
		
		model.addAttribute("goodsDto", goodsDto);
		model.addAttribute("reviewDtos", reviewDtos);

		/* 상품 문의 부분 */
		model.addAttribute("aQnaDtos", aQnaDtos);

		log.info("g_no>>>>>>>>>>>>>>>>>>>>>>" + g_no);

		return nextPage;
	}
	
	// 상품 정보 가져오기
	@GetMapping("/getProductInfo")
	public ResponseEntity<List<CategoryDto>> getProductInfo() {
		log.info("getProductInfo()");
		
		List<CategoryDto> categoryDtos = goodsService.getGoodsInfo();
		
		return ResponseEntity.ok(categoryDtos);
	}

	/*
	 * 상품 수정
	 */
	@GetMapping("/modifyGoods")
	public String modifyGoods(@RequestParam("g_no") int g_no, @RequestParam("c_no") int c_no, Model model, HttpSession session) {
		log.info("modifyGoods()");

		String nextPage = "admin/goods/modify_goods_form";
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
		if(adminMemberDto == null)
			return "redirect:/admin/member/login_form";

		GoodsDto goodsDto = goodsService.modifyGoods(g_no);
		model.addAttribute("goodsDto", goodsDto);

		log.info("modifyGoods() : g_no >>>>>>>>>>>>>> " + g_no);

		return nextPage;
	}

	/*
	 * 상품 수정 confirm
	 */
	@PostMapping("/modifyGoodsConfirm")
	@ResponseBody
	public Object modifyGoodsConfirm(GoodsDto goodsDto, @RequestParam("file1") MultipartFile file1,
			@RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3,
			@RequestParam("file4") MultipartFile file4, @RequestParam("c_no") int c_no,
			@RequestParam("c_no2") int c_no2, HttpSession session, Model model) {

		log.info("modifyGoodsConfirm()");
		
		model.addAttribute(AdminConfig.ATTRIBUTE_NAME, adminConfig);
		
		// 클라이언트에서 받은 c_no와 c_parents_no 값을 이용하여 상품 정보 수정
	    goodsDto.setC_no(c_no);
	    goodsDto.setC_parents_no(c_no2);


		String nextPage = "admin/goods/goods_modify_success";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("file1", file1.getResource());
		requestBody.add("file2", file2.getResource());
		requestBody.add("file3", file3.getResource());
		requestBody.add("file4", file4.getResource());

		HttpEntity<MultiValueMap<String, Object>> responseEntity = new HttpEntity<>(requestBody, headers);

		String serverURL = "http://14.42.124.95:8091/upload_file";
//		String serverURL = "http://192.168.200.126:8091/upload_file";

		ResponseEntity<Object> response = restTemplate.postForEntity(serverURL, responseEntity, Object.class);

		Map<String, String> map = (Map<String, String>) response.getBody();
		String savedFileName1 = map.get("savedFileName1"); // 새파일명 or null
		String savedFileName2 = map.get("savedFileName2"); // 새파일명 or null
		String savedFileName3 = map.get("savedFileName3"); // 새파일명 or null
		String savedFileName4 = map.get("savedFileName4"); // 새파일명 or null
		
		if (map != null) {
			if (file1 != null && !file1.isEmpty()) {
				
				goodsDto.setG_thumbnail_name(savedFileName1);
				log.info("file1"+ file1);
				
			}
	
			if (file2 != null && !file2.isEmpty()) {
				
				goodsDto.setG_second_img_name(savedFileName2);
				log.info("file2"+ file2);
				
			}
	
			if (file3 != null && !file3.isEmpty()) {
				
				goodsDto.setG_third_img_name(savedFileName3);
				log.info("file3"+ file3);
				
			}
	
			if (file4 != null && !file4.isEmpty()) {
				
				goodsDto.setG_detail_content(savedFileName4);
				log.info("file4"+ file4);
				
			}

			// hidden input 필드에서 값 가져오기
//		    c_no = Integer.parseInt(request.getParameter("c_no"));
//		    c_parents_no = Integer.parseInt(request.getParameter("c_parents_no"));
		    
			int result = goodsService.modifyGoodsConfirm(goodsDto);
//			int result = goodsService.modifyGoodsConfirm(goodsDto, c_no, c_parents_no); // 원래 코드. 만약 상품 수정이 안되거나 오류가 나면 여기 원복 후 아래 추가 코드 삭제
			//int result = goodsService.modifyGoodsConfirm(goodsDto, c_no);
			
			log.info("수정 이미지 >>>>>>>>>> "+goodsDto.getG_thumbnail_name());
			log.info("수정 c_no >>>>>>>>>> "+c_no);
			log.info("수정 goodsDto.c_no >>>>>>>>>> "+goodsDto.getC_no());
			log.info("수정 goodsDto.c_parents_no >>>>>>>>>> "+goodsDto.getC_parents_no());

			if (result <= 0) {

				nextPage = "admin/goods/goods_modify_fail";
			}

		}

		return nextPage;
	}
	
	/*
	 * 상품 삭제
	 */
	@GetMapping("/deleteGoods")
	public String deleteGoods(@RequestParam("g_no") int g_no, HttpSession session) {
		log.info("deleteGoods()");
		


		AdminMemberDto adminMemberDto = (AdminMemberDto) session.getAttribute("adminMemberDto");
		if(adminMemberDto == null)
			return "redirect:/admin/member/login_form";

		int result = goodsService.deleteGoodsConfirm(g_no);
		if(result < 0) {
			
			return "redirect:/admin/goods/goodsList";
			
		} else {
			
			return "redirect:/admin/goods/goodsList";
			
		}
		
	}
	
	// 상품 승인
	@GetMapping("/updateApproval")
	public String updateApproval(@RequestParam("g_no") int g_no) {
		log.info("updateApproval()");
		log.info("승인) g_no >>>>>>>>>> " + g_no);
		

		
		String nextPage = "redirect:/admin/goods/goodsList";
		
		int result = goodsService.updateApproval(g_no);
		
		if(result < 0)
			return "redirect:/admin/goods/goodsList";
		
		return nextPage;
	}

}

