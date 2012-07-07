package com.raptoz.mypage;

import org.apache.catalina.util.Base64;
import org.bson.types.ObjectId;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.raptoz.tag.Tag;
import com.raptoz.user.User;
import com.raptoz.user.UserService;
import com.raptoz.util.RaptozUtil;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MyPageService mypageService;
	@Autowired UserService userService;
	
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
//
//	@RequestMapping("/{userId}/tag/add")
//	@ResponseBody
//	public Tag addTag(@PathVariable Long userId, @RequestParam("tag") String tag) {
//		logger.info("추가할 태그: " + tag);
//		Tag insertedTag = mypageService.addTag(userId, tag);
//		return insertedTag;
//	}
//
//	@RequestMapping("/{userId}/tag/{tagId}/delete")
//	@ResponseBody
//	public String deleteTag(@PathVariable Long userId, @PathVariable Long tagId) {
//		mypageService.deleteTag(tagId);
//		// 조건 작성
//		return "true";
//	}
//
//	@RequestMapping(value="/{userId}/profileImage/update", method=RequestMethod.POST)
//	@ResponseBody
//	public String updateProfileImage(@PathVariable Long userId, @RequestParam("profileImage") MultipartFile profileImage) {
//		mypageService.updateProfileImage(userId, profileImage);
//		return Base64.encode(RaptozUtil.getBytes(profileImage));
//	}
}