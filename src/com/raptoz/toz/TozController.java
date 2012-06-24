package com.raptoz.toz;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.raptoz.user.*;

@Controller
@RequestMapping("/toz")
@SessionAttributes("loginUser")
public class TozController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private TozService tozService;
	
	@RequestMapping("/create")
	public String create(@ModelAttribute("loginUser") User loginUser, Toz toz) {
		logger.info(loginUser.toString());
		logger.info(toz.toString());
		
		tozService.create(loginUser, toz);
		return "redirect:/list";
	}
	
	@RequestMapping("/{tozId}")
	@ResponseBody
	public Toz get(@PathVariable Long tozId) {
		logger.info("Toz ID:" + tozId);
		return tozService.get(tozId);
	}
	
}
