package com.dogdam.shop.user.bookmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogdam.shop.admin.goods.GoodsDto;
import com.dogdam.shop.admin.member.AdminMemberDto;
import com.dogdam.shop.user.member.MemberDto;
import com.dogdam.shop.user.qa.QnaDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/user/bookmark")
public class BookmarkController {

	@Autowired
	BookmarkService bookmarkService; 
	
	@PostMapping("/bookmarkOn")
	@ResponseBody
	public Object bookmarkOn(@RequestBody Map<String, String> msgMap, HttpSession session, BookmarkDto bookmarkDto) {
	    log.info("bookmarkOn()");

		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		bookmarkDto.setU_id(loginedMemberDto.getU_id());

		int g_no = Integer.parseInt(msgMap.get("g_no"));
		bookmarkDto.setG_no(g_no);
		
		String u_id = bookmarkDto.getU_id();				
	    
	    log.info("g_no>>>>>>>>>>>>>>>>>>>>>>" + g_no);
	    log.info("u_id>>>>>>>>>>>>>>>>>>>>>>" + u_id);

	    int notbookmarked = bookmarkService.bookmarkOn(g_no, u_id);	    

	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("notbookmarked", notbookmarked);

	    return resultMap;
	}
	
	
	@PostMapping("/bookmarkOff")
	@ResponseBody
	public Object bookmarkOff(@RequestBody Map<String, String> msgMap, HttpSession session, BookmarkDto bookmarkDto) {
	    log.info("bookmarkOff()");

		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		bookmarkDto.setU_id(loginedMemberDto.getU_id());

		int g_no = Integer.parseInt(msgMap.get("g_no"));
		bookmarkDto.setG_no(g_no);
		
		String u_id = bookmarkDto.getU_id();				
	    
	    log.info("g_no>>>>>>>>>>>>>>>>>>>>>>" + g_no);
	    log.info("u_id>>>>>>>>>>>>>>>>>>>>>>" + u_id);

	    int bookmarked = bookmarkService.bookmarkOff(g_no, u_id);	    

	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("bookmarked", bookmarked);

	    return resultMap;
	}
	
	@GetMapping("/bookmarkList")
	public String bookmarkList(HttpSession session, Model model) {
		log.info("bookmarkList()");
		
     
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
        String nextPage = "/user/bookmark/bookmark_list";
				
		List<BookmarkDto> bookmarkDtos = bookmarkService.bookmarkList(loginedMemberDto.getU_id());
		
		       
        model.addAttribute("bookmarkDtos", bookmarkDtos);
		
		return nextPage;
	}
	
	@GetMapping("/deleteBookmarkConfirm")
	public String deleteBookmarkConfirm(@RequestParam("g_no") int g_no, @RequestParam("bm_no") int bm_no, HttpSession session) {
		log.info("deleteBookmarkConfirm()");
		
		String nextPage = "redirect:/user/bookmark/bookmarkList";
		
		MemberDto loginedMemberDto = 
				(MemberDto) session.getAttribute("loginedMemberDto");
		
		if(loginedMemberDto == null) {
			return "user/member/login_form";
		}
		
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setG_no(g_no);  
        
	    log.info("g_no>>>>>>>>>>>>>>>>>>>>>>" + g_no);
	    log.info("bm_no>>>>>>>>>>>>>>>>>>>>>>" + bm_no);
		
		int result = bookmarkService.deleteBookmarkConfirm(bm_no, g_no);
		
		if(result <= 0)
			nextPage = "/user/bookmark/delete_bookmark_ng";
			
		return nextPage;
	}
	
		
}
