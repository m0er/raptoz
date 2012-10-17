package com.raptoz.main;

import java.util.List;

import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.raptoz.message.Message;
import com.raptoz.message.MessageService;
import com.raptoz.search.*;
import com.raptoz.user.*;

@Controller
@SessionAttributes("loginUser")
public class MainController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SearchService searchService;
	@Autowired MessageService messageService;
	
	@RequestMapping("/index")
	public String index() {
		return "main/index";
	}
	
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			logger.info("user in session: " + loginUser.toString());
			List<Message> notifications = messageService.getByReceiverId(loginUser.getId());
			model.addAttribute("notifications", notifications);
			model.addAttribute("notificationCount", notifications.size());
		}
		
		Search search = searchService.recent();
		model.addAttribute("search", search);
		
		return "main/list";
	}
	
	@RequestMapping("/list/{term}")
	public String search(@PathVariable String term, Model model) {
		Search search = searchService.search(term);
		model.addAttribute("search", search);
		
		return "main/list";
	}
}
