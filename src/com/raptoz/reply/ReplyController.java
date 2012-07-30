package com.raptoz.reply;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.raptoz.post.Post;
import com.raptoz.post.PostService;
import com.raptoz.user.User;

@Controller
@RequestMapping("/reply")
@SessionAttributes("loginUser")
public class ReplyController {
	@Autowired ReplyService replyService;
	@Autowired PostService postService;
	
	@RequestMapping("/add")
	@ResponseBody
	public Reply add(Reply reply, @ModelAttribute("loginUser") User user) {
		reply = replyService.add(user, reply);
		
		updatePost(reply);
		
		return reply;
	}

	private void updatePost(Reply reply) {
		Post post = postService.get(reply.getPostId());
		List<ObjectId> replyIds = post.getReplyIds();
		replyIds.add(reply.getId());
		post.setReplyIds(replyIds);
		
		postService.update(post);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable ObjectId id) {
		replyService.delete(id);
		return "success";
	}
	
	@RequestMapping("/{postId}")
	@ResponseBody
	public List<Reply> get(@PathVariable ObjectId postId) {
		List<Reply> replyList = replyService.get(postId);
		return replyList;
	}
}
