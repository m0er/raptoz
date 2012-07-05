package com.raptoz.reply;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.raptoz.user.User;

@Controller
@RequestMapping("/reply")
@SessionAttributes("loginUser")
public class ReplyController {
	@Autowired ReplyService replyService;
	
	@RequestMapping("/add")
	@ResponseBody
	public Reply add(Reply reply, @ModelAttribute("loginUser") User user) {
		reply.setWriter(user);
		replyService.add(reply);
		return reply;
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable ObjectId id) {
		replyService.delete(id);
		return "success";
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public List<Reply> get(@PathVariable ObjectId id) {
		List<Reply> replyList = replyService.get(id);
		return replyList;
	}
}
