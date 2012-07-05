package com.raptoz.search;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.raptoz.post.*;
import com.raptoz.user.*;

@Service("searchService")
public class SearchService {
	@Autowired PostService postService;
	//@Autowired UserService userService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
//	public Search search(String term) {
//		Search search = new Search();
//		search.setTozList(postService.getByTagWithAllTags(term));
//		search.setUserList(userService.getByTagWithTagAndToz(term));
//		logger.info(search.toString());
//		return search;
//	}
}
