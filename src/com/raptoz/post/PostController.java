package com.raptoz.post;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.raptoz.tag.TagService;
import com.raptoz.user.User;

@Slf4j
@Controller
@RequestMapping("/post")
@SessionAttributes("loginUser")
public class PostController {
	@Autowired private PostService postService;
	@Autowired private TagService tagService;
	
	@RequestMapping("/write")
	public String create(@ModelAttribute("loginUser") User user, Post post, @RequestHeader("referer") String referer) {
		postService.create(user, post);
		tagService.upsert(post.getTags());
		
		return "redirect:" + referer;
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute("loginUser") User user, Post post, String originalPostId, @RequestHeader("referer") String referer) {
		ObjectId id = new ObjectId(originalPostId);
		Post originalPost = postService.get(id);
		originalPost.setContent(post.getContent());
		originalPost.setTitle(post.getTitle());
		originalPost.setTags(post.getTags());
		
		postService.create(user, originalPost);
		
		return "redirect:" + referer;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Post get(@PathVariable ObjectId id) {
		log.info("Post Id:" + id);
		return postService.increaseViewCount(id);
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable ObjectId id, @RequestHeader("referer") String referer) {
		log.info("Post Id:" + id);
		log.info("referer:" + referer);
		
		postService.deleteById(id);
		return "redirect:" + referer;
	}
}
