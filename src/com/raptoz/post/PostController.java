package com.raptoz.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.raptoz.user.User;

@Controller
@RequestMapping("/post")
@SessionAttributes("loginUser")
public class PostController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private PostService postService;
	
	@RequestMapping("/create")
	public String create(@ModelAttribute("loginUser") User user, Post post) {
		logger.info(user.toString());
		logger.info(post.toString());
		
		postService.create(user, post);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Post get(@PathVariable("id") Post post) {
		logger.info("Post:" + post.toString());
		return post;
	}
	
}
