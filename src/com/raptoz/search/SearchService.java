package com.raptoz.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raptoz.post.PostService;
import com.raptoz.user.UserService;

@Service("searchService")
public class SearchService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired private PostService postService;
	@Autowired private UserService userService;
	
	public Search search(String term) {
		Search search = new Search();
		search.setPosts(postService.getByTag(term));
		search.setUsers(userService.getByTag(term));
		
		logger.info(search.toString());
		
		return search;
	}
}
