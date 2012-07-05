package com.raptoz.user;

import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.*;
import org.springframework.web.multipart.*;

@Controller
@RequestMapping("/user")
@SessionAttributes("loginUser")
public class UserController {
//	Logger logger = LoggerFactory.getLogger(getClass());
//	@Autowired UserService userService;
//	
//	@RequestMapping(value="/signup", method=RequestMethod.POST)
//	public String signup(User user, @RequestParam("profileImage") MultipartFile profileImage) {
//		logger.info(user.toString());
//		logger.info("File '" + profileImage.getOriginalFilename() + "' uploaded successfully");
//		userService.add(user, profileImage);
//		return "redirect:/list";
//	}
//	
//	@RequestMapping("/login")
//	public String login(User user, @RequestHeader("referer") String referer, Model model) {
//		logger.info("login() - " + user.toString());
//		logger.info(referer);
//		
//		user = userService.login(user);
//		if (user != null) {
//			model.addAttribute("loginUser", user);
//			logger.info("login success - " + user);
//		}
//		
//		return "redirect:" + referer;
//	}
//	
//	@RequestMapping("/logout")
//	public String logout(@RequestHeader("referer") String referer, SessionStatus sessionStatus) {
//		sessionStatus.setComplete();
//		return "redirect:" + referer;
//	}
//	
//	@RequestMapping(value="/islogin", method=RequestMethod.GET)
//	@ResponseBody
//	public Object isLogin(HttpSession session) {
//		User loginUser = (User) session.getAttribute("loginUser");
//		return loginUser == null ? false : loginUser;
//	}
}
