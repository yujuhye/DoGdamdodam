package com.dogdam.shop.user.bookmark;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IBookmarkDao {

	BookmarkDto selectBookmarked(Map<Object, Object> bookmarkHeartMap);

	int insertBookmark(Map<Object, Object> bookmarkOnMap);

	int plusBookcount(Map<Object, Object> bookmarkOnMap);
	
	int minusBookcount(Map<Object, Object> bookmarkOnMap);

	int deleteBookmarkForHeart(Map<Object, Object> bookmarkOnMap);

	List<BookmarkDto> selectBookmarks(String u_id);

	int deleteBookmarkForList(Map<Object, Object> deleteBookmarkMap);


	
}