package com.raptoz.post;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.raptoz.security.SecurityService;
import com.raptoz.tag.TagService;

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired TagService tagService;
	@Autowired PostService postService;
	@Autowired SecurityService securityService;
	
	@RequestMapping("/write")
	public String create(Post post, @RequestHeader("referer") String referer) {
		postService.create(securityService.getCurrentUser(), post);
		tagService.upsert(post.getTags());
		
		return "redirect:" + referer;
	}
	
	@RequestMapping("/modify")
	public String modify(Post post, String originalPostId, @RequestHeader("referer") String referer) {
		ObjectId id = new ObjectId(originalPostId);
		Post originalPost = postService.get(id);
		originalPost.setContent(post.getContent());
		originalPost.setTitle(post.getTitle());
		originalPost.setTags(post.getTags());
		
		postService.create(securityService.getCurrentUser(), originalPost);
		
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
