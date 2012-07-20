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
import com.raptoz.user.UserRepository;
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
		model.addAttribute("user", userService.getById(id));
		return "mypage/form";
	}

//	@RequestMapping(value="/{userId}/update", method=RequestMethod.POST)
//	public String updateUser(@PathVariable Long userId, PersonalInfo personalInfo, Model model) {
//		logger.info("updateUser() called");
//		User user = mypageService.updateUser(userId, personalInfo);
//		if (user != null) {
//			return "redirect:/mypage/" + user.getId();
//		}
//		return null;
//	}

	@RequestMapping("/{id}/tag/add")
	@ResponseBody
	public Tag addTag(@PathVariable("id") ObjectId userId, Tag tag) {
		logger.info("추가할 태그: " + tag);
		Tag insertedTag = tagService.addTag(tag);
		
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
		/*
		 * ToDo 
		 * 
		 * tagId 소스 수정
		 * 삭제 정상작동하지 않음
		 */
		if (mypageService.removeTag(userId, tagId) != null) {
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
}