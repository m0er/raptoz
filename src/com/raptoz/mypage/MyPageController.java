package com.raptoz.mypage;

import java.util.ArrayList;

import java.util.List;

import org.apache.catalina.util.Base64;
import org.bson.types.ObjectId;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.raptoz.tag.Tag;
import com.raptoz.tag.TagService;
import com.raptoz.user.User;
import com.raptoz.user.UserService;
import com.raptoz.util.RaptozUtil;

@Controller
@RequestMapping("/mypage")
@SessionAttributes("loginUser")
public class MyPageController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MyPageService mypageService;
	@Autowired UserService userService;
	@Autowired TagService tagService;
	
	@RequestMapping("/{id}")
	public String mypage(@PathVariable("id") ObjectId id, Model model) {
		logger.info("사용자 아이디: " + id);
		User user = userService.getById(id);
		model.addAttribute("user", user);
		model.addAttribute("posts", user.getActivities());
		
		return "mypage/form";
	}

	@RequestMapping(value="/{userId}/update", method=RequestMethod.POST)
	public String updateUser(@PathVariable ObjectId userId, PersonalInfo personalInfo, Model model) {
		logger.info("updateUser() called");
		User user = mypageService.update(userId, personalInfo);
		if (user != null) {
			return "redirect:/mypage/" + user.getId();
		}
		return null;
	}
	
	@RequestMapping("/{userId}/tag/add")
	@ResponseBody
	public Tag insertTag(@PathVariable("userId") ObjectId userId, Tag tag) {
		logger.info("추가할 태그: " + tag);
		Tag insertedTag = tagService.upsert(tag);
		
		User user = userService.getById(userId);
		
		List<Tag> tags = user.getTags();
		
		if (tags == null) {
			tags = new ArrayList<>();
		}
		
		tags.add(insertedTag);
		user.setTags(tags);
		userService.updateUser(user);
		return insertedTag;
	}

	@RequestMapping("/{userId}/tag/{tagId}/delete")
	@ResponseBody
	public boolean deleteTag(@PathVariable("userId") ObjectId userId, @PathVariable("tagId") ObjectId tagId) {
		Tag tag = mypageService.removeTag(userId, tagId);
		if (tag != null) {
			tagService.delete(tag);
			return true;
		}
		return false;
	}

	@RequestMapping(value="/{id}/profileImage/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateProfileImage(@PathVariable("id") ObjectId userId, @RequestParam("profileImage") MultipartFile profileImage) {
		mypageService.updateProfileImage(userId, profileImage);
		return Base64.encode(RaptozUtil.getBytes(profileImage));
	}
	
	@RequestMapping(value="/{id}/verify", method=RequestMethod.POST)
	@ResponseBody
	public boolean verifyPassword(@PathVariable("id") ObjectId userId, @RequestParam("pwd") String password) {
		return mypageService.isVerify(userId, password);
	}
	
	@RequestMapping(value="/{id}/verifyNP", method=RequestMethod.POST)
	@ResponseBody
	public boolean verifyNewPassword(@PathVariable("id") ObjectId userId, 
			@RequestParam("newPwd") String newPwd, @RequestParam("confirmPwd") String confirmPwd) {
		return mypageService.isEqual(newPwd, confirmPwd);
	}
}