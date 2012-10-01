package com.raptoz.dummy;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test/dummy")
public class DummyDataController {
	@Autowired DummyDataService dummyDataService;
	
	@RequestMapping("/create")
	public String create() {
		dummyDataService.create();
		return "redirect:/list";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		dummyDataService.delete();
		return "redirect:/list";
	}
}
