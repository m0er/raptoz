package com.raptoz.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raptoz.post.PostService;
import com.raptoz.user.User;
import com.raptoz.user.UserService;

@Service("searchService")
public class SearchService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired private PostService postService;
	@Autowired private UserService userService;
	
	private static final int RECENT_LIMIT = 10;
	
	public Search search(String term) {
		Search search = new Search();
		search.setPosts(postService.getByTag(term));
		
		List<User> users = userService.getByTag(term);
		
		search.setUsers(users);
		
		logger.info("search " + term + ":\n" + search.toString());
		
		return search;
	}

	public Search recent() {
		Search search = new Search();
		search.setPosts(postService.getRecent(RECENT_LIMIT));
		
		List<User> users = userService.getRecent(RECENT_LIMIT);
		
		search.setUsers(users);
		
		logger.info("search recent" + ":\n" + search.toString());
		
		return search;
	}
}
