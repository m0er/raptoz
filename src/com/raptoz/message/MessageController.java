package com.raptoz.message;

import java.util.Date;

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
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MessageService messageService;
	
	@RequestMapping("/send/{id}")
	public void send(@ModelAttribute("loginUser") User from, @PathVariable("id") User to, Message message) {
		message.setSent(new Date());
		message.setFrom(from);
		message.setTo(to);
		
		messageService.send(message);
	}
	
}
