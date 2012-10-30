package com.raptoz.user;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.raptoz.tag.TagService;

@Slf4j
@Controller
@RequestMapping("/user")
@SessionAttributes("loginUser")
public class UserController {
	@Autowired UserService userService;
	@Autowired TagService tagService;
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(User user, @RequestParam("profileImage") MultipartFile profileImage) {
		log.info(user.toString());
		log.info("File '" + profileImage.getOriginalFilename() + "' uploaded successfully");
		
		userService.add(user, profileImage);
		tagService.upsert(user.getTags());
		
		return "redirect:/list";
	}
	
	@RequestMapping("/login")
	public String login(User user, @RequestHeader("referer") String referer, Model model) {
		log.info("login() - " + user.toString());
		log.info(referer);
		
		user = userService.login(user);
		if (user != null) {
			model.addAttribute("loginUser", user);
			log.info("login success - " + user);
		}
		
		return "redirect:" + referer;
	}
	
	@RequestMapping("/logout")
	public String logout(@RequestHeader("referer") String referer, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:" + referer;
	}
	
	@RequestMapping(value="/islogin", method=RequestMethod.GET)
	@ResponseBody
	public boolean isLogin(HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		return loginUser == null ? false : true;
	}
	
}
