package com.raptoz.message;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.raptoz.user.User;

@Controller
@RequestMapping("/message")
@SessionAttributes("loginUser")
public class MessageController {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private MessageService messageService;
	
	@RequestMapping("/send/{userId}")
	@ResponseBody
	public String send(@ModelAttribute("loginUser") User from, @PathVariable("userId") User to, Message message, HttpSession session) {
		message.setSent(new Date());
		message.setFrom(from);
		message.setTo(to);
		
		messageService.send(message);
		
		return "success";
	}
	
	@RequestMapping("/list/{receiverId}")
	@ResponseBody
	public List<Message> list(@PathVariable("receiverId") ObjectId receiverId) {
		return messageService.getByReceiverId(receiverId);
	}
	
}
