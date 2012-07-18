package com.raptoz.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raptoz.activity.ActivityService;
import com.raptoz.post.PostService;
import com.raptoz.user.User;
import com.raptoz.user.UserService;

@Service("searchService")
public class SearchService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired private PostService postService;
	@Autowired private UserService userService;
	@Autowired private ActivityService activityService;
	
	public Search search(String term) {
		Search search = new Search();
		search.setPosts(postService.getByTag(term));
		
		List<User> users = userService.getByTag(term);
		for (int i = 0; i < users.size(); i++) {
			User user  = users.get(i);
			user.setActivities(activityService.getByUser(user));
		}
		
		search.setUsers(users);
		
		logger.info(search.toString());
		logger.info("Posts:" + search.getPosts().size());
		logger.info("Users:" + search.getUsers().size());
		
		return search;
	}
}
