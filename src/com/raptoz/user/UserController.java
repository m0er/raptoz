package com.raptoz.user;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Strings;
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
		log.info("signup user info: {}", user.toString());
		log.info("File '{}' uploaded successfully", profileImage.getOriginalFilename());
		
		userService.add(user, profileImage);
		tagService.upsert(user.getTags());
		
		return "redirect:/list";
	}
	
	@RequestMapping("/login")
	public String login(User user, @RequestHeader("referer") String referer, String redirectUrl, HttpSession session, RedirectAttributes redirectAttr) {
		log.info("login(): {}", user.toString());
		log.info("referer: {}", referer);
		log.info("redirectUrl: {}", redirectUrl);
		
		User savedUser = userService.login(user);
		if (savedUser != null) {
			session.setAttribute("loginUser", savedUser);
			log.info("login success - {}", savedUser);
			
			if (!Strings.isNullOrEmpty(redirectUrl)) {
				return "redirect:" + redirectUrl;
			} else {
				return "redirect:" + referer;
			}
		} else {
			User userByEmail = userService.getByEmail(user.getEmail());
			
			String emailIncorrectMessage = "";
			String passwordIncorrectMessage = "";
			if (userByEmail == null) {
				emailIncorrectMessage = "check your email";
			} else {
				passwordIncorrectMessage = "check your password";
			}
			
			redirectAttr.addFlashAttribute("redirectUrl", referer)
						.addFlashAttribute("emailIncorrectMessage", emailIncorrectMessage)
						.addFlashAttribute("passwordIncorrectMessage", passwordIncorrectMessage)
						.addFlashAttribute("email", user.getEmail())
						.addFlashAttribute("password", user.getPassword());
			
			return "redirect:/user/login/failure";
		}
	}
	
	@RequestMapping({"/login/failure", "/login/form"})
	public String loginFailure(Model model) {
		return "login/form";
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
