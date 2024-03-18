package com.dogdam.shop.user.qa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogdam.shop.admin.goods.GoodsDto;
import com.dogdam.shop.user.member.MemberDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
@RequestMapping("/user/qa")
public class QnaController {
	
	@Autowired
	QnaService qnaService; 
	
	
	/* 굿즈의 상세페이지로 이동함
	 * 
	 * @GetMapping("/qnaListforGoods") public String
	 * qnaListforGoods(@RequestParam("g_no") int g_no, GoodsDto goodsDto, QnaDto
	 * qnaDto, Model model) { log.info("qnaListforGoods()");
	 * 
	 * String nextPage = "admin/goods/goods_detail_view";
	 * 
	 * goodsDto.setG_no(g_no); 
	 * List<QnaDto> qnaDtos = qnaService.qnaListforGoods(g_no);
	 * 
	 * 
	 * model.addAttribute("goodsDto", goodsDto); 
	 * model.addAttribute("qnaDtos", qnaDtos);
	 * 
	 * log.info("g_no>>>>>>>>>>>>>>>>>>>>>>"+g_no);
	 * 
	 * return nextPage; }
	 */
	
	@GetMapping("/qnaListforUser")
	public String qnaListforUser( 
			GoodsDto goodsDto, HttpSession session, Model model) {
		log.info("qnaListforUser()");
		
        String nextPage = "/user/qa/qna_list";
        
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
		List<QnaDto> qnaDtos = qnaService.qnaListforUser(loginedMemberDto.getU_id());
		       
        model.addAttribute("qnaDtos", qnaDtos);
		
		return nextPage;
	}
	
	@GetMapping("/deleteQnaConfirm")
	public String deleteQnaConfirm(@RequestParam("gqa_no") int gqa_no, HttpSession session) {
		log.info("deleteQnaConfirm()");
		
		String nextPage = "redirect:/user/qa/qnaListforUser";
		
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
		int result = qnaService.deleteQnaConfirm(gqa_no);
		
		if(result <= 0)
			nextPage = "/user/qa/delete_qna_ng";
			
		return nextPage;
	}
	
	@GetMapping("/createQnaForm")
	public String createQnaForm(@RequestParam("g_no") int g_no, HttpSession session, GoodsDto goodsDto, QnaDto qnaDto, Model model) {
		log.info("createQnaForm()");
		
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
        String nextPage = "/user/qa/create_qna_form";
        
        goodsDto = new GoodsDto();
        goodsDto.setG_no(g_no);
        
        qnaDto = new QnaDto();
        		
        model.addAttribute("goodsDto", goodsDto); 
        model.addAttribute("qnaDto", qnaDto);
        
        log.info("g_no>>>>>>>>>>>>>>>>>>>>>>"+g_no);
        
        return nextPage;
    }
	
	@PostMapping("/createQnaConfirm")
	public String createQnaConfirm(@RequestParam("g_no") int g_no, GoodsDto goodsDto, QnaDto qnaDto, HttpSession session, Model model) {
	    log.info("createQnaConfirm()");
	    
	    MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");
	    
	    String u_id = loginedMemberDto.getU_id();
	   	    	 
	    model.addAttribute("qnaDto", qnaDto);
	    log.info("g_no>>>>>>>>>>>>>>>>>>>>>>"+g_no);
	    
	    String nextPage = "redirect:/user/goods/goodsDetailView?g_no=" + g_no;
	    
	    int result = qnaService.createQnaConfirm(g_no, u_id, qnaDto);
	    
	    if (result <= 0) {
	        nextPage = "/user/qa/create_privateqna_ng";
	    }
	    
	    return nextPage;
	}
								
}
