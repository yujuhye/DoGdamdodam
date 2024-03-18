package com.dogdam.shop.user.bookmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogdam.shop.user.qa.QnaDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BookmarkService {

	
	@Autowired
	IBookmarkDao iBookmarkDao;
	
	public boolean bookmarkHeart(int g_no, String u_id) {
		log.info("bookmarkHeart()");
		
		log.info("g_no>>>>>>>>>>>>>>>>>>>>>>" + g_no);
		log.info("u_id>>>>>>>>>>>>>>>>>>>>>>" + u_id);
		
		Map<Object, Object> bookmarkHeartMap = new HashMap<>();
		bookmarkHeartMap.put("g_no", g_no);
		bookmarkHeartMap.put("u_id", u_id);
						
		BookmarkDto isBookmarked = iBookmarkDao.selectBookmarked(bookmarkHeartMap);
		
		if (isBookmarked != null) {
			
			return true;
			
		} else {
			
			return false;
		}
	}

	public int bookmarkOn(int g_no, String u_id) {		
		log.info("bookmarkOn()");
     
        
        Map<Object, Object> bookmarkOnMap = new HashMap<>();
        bookmarkOnMap.put("g_no", g_no);
        bookmarkOnMap.put("u_id", u_id);
        
	    log.info("g_no>>>>>>>>>>>>>>>>>>>>>>" + g_no);
	    log.info("u_id>>>>>>>>>>>>>>>>>>>>>>" + u_id);
        
        int result = -1; 
        result = iBookmarkDao.insertBookmark(bookmarkOnMap);
        
          if(result > 0 ) {
        	
        	return iBookmarkDao.plusBookcount(bookmarkOnMap);
                                              
        }
          
		return result;     
				
	}
	
	public int bookmarkOff(int g_no, String u_id) {		
		log.info("bookmarkOff()");
     
        
        Map<Object, Object> bookmarkOffMap = new HashMap<>();
        bookmarkOffMap.put("g_no", g_no);
        bookmarkOffMap.put("u_id", u_id);
        
	    log.info("g_no>>>>>>>>>>>>>>>>>>>>>>" + g_no);
	    log.info("u_id>>>>>>>>>>>>>>>>>>>>>>" + u_id);
        
        int result = -1; 
        result = iBookmarkDao.minusBookcount(bookmarkOffMap);
        
          if(result > 0 ) {
        	
        	return iBookmarkDao.deleteBookmarkForHeart(bookmarkOffMap);
                                              
        }
          
		return result;    		
		
	}

	public List<BookmarkDto> bookmarkList(String u_id) {

		log.info("u_id>>>>>>>>>>>>>>>>>>>>>>" + u_id);
		return iBookmarkDao.selectBookmarks(u_id);
		
		}

	public int deleteBookmarkConfirm(int bm_no, int g_no) {
		log.info("deleteQnaConfirm()");
		
		  Map<Object, Object> deleteBookmarkMap = new HashMap<>();
		  deleteBookmarkMap.put("g_no", g_no);
		  deleteBookmarkMap.put("bm_no", bm_no);
		
	       int result = -1; 
	        result = iBookmarkDao.minusBookcount(deleteBookmarkMap);
	        
	          if(result > 0 ) {
	        	
	        	return iBookmarkDao.deleteBookmarkForList(deleteBookmarkMap);
	                                              
	        }
	          
			return result;   
		
	}

	}
		