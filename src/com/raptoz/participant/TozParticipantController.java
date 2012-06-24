package com.raptoz.participant;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.raptoz.toz.*;
import com.raptoz.user.*;

@Controller
@RequestMapping("/participant")
@SessionAttributes("loginUser")
public class TozParticipantController {
	@Autowired TozParticipantService tozParticipantService;
	
	@RequestMapping("/add")
	@ResponseBody
	public TozParticipant add(TozParticipant tozParticipant, @ModelAttribute("loginUser") User loginUser) {
		tozParticipant.setUserId(loginUser.getId());
		tozParticipantService.add(tozParticipant);
		tozParticipant.setParticipant(loginUser);
		return tozParticipant;
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable Long id) {
		tozParticipantService.delete(id);
		return "success";
	}
	
	@RequestMapping("/{tozId}")
	@ResponseBody
	public List<TozParticipant> get(@PathVariable Long tozId) {
		return tozParticipantService.get(tozId);
	}
}
