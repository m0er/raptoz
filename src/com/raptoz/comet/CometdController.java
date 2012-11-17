package com.raptoz.comet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CometdController {
	
	@RequestMapping("/hello/cometd")
	public String helloCometd() {
		return "cometd/index";
	}
	
	@RequestMapping("/chat/cometd")
	public String chatCometd() {
		return "cometd/chat/index";
	}
}
