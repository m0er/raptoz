package com.raptoz.post;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.raptoz.tag.TagService;
import com.raptoz.user.User;

@Controller
@RequestMapping("/post")
@SessionAttributes("loginUser")
public class PostController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private PostService postService;
	@Autowired private TagService tagService;
	
	@RequestMapping("/write")
	public String create(@ModelAttribute("loginUser") User user, Post post) {
		logger.info(user.toString());
		logger.info(post.toString());
		
		postService.create(user, post);
		tagService.upsert(post.getTags());
		
		return "redirect:/list";
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Post get(@PathVariable ObjectId id) {
		logger.info("Post Id:" + id);
		return postService.getById(id);
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable ObjectId id, @RequestHeader("referer") String referer) {
		logger.info("Post Id:" + id);
		logger.info("referer:" + referer);
		
		postService.deleteById(id);
		return "redirect:" + referer;
	}
	
}
