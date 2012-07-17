package com.raptoz.mypage;


import java.util.List;

import org.apache.catalina.util.Base64;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.raptoz.tag.Tag;
import com.raptoz.user.User;
import com.raptoz.user.UserRepository;
import com.raptoz.util.RaptozUtil;

@Service("mypageService")
public class MyPageService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserRepository userRepository;
	
//	public User getUser(ObjectId id) {
//		return userRepository.findOne(id);
//	}
		
//	public User updateUser(ObjectId userId, PersonalInfo personalInfo) {
//		if (userMapper.isVerifyPassword(getUser(userId).getEmail(), personalInfo.getCurPwd())) {
//			logger.info("failed identify password");
//			return null;
//		}
//		
//		String newPwd = personalInfo.getNewPwd();
//		String confirmPwd = personalInfo.getConfirmPwd();
//		if (newPwd.trim().length() == 0 || confirmPwd.trim().length() == 0 || !isEqual(newPwd, confirmPwd)) {
//			logger.info("failed apply new password");
//			return null;
//		}
//		
//		userMapper.deleteById(userId);
//		User user = new User(personalInfo.getEmail(), newPwd, personalInfo.getNickname(), "");
//		userMapper.save(user);
//		logger.info("update success");
//		
//		return user;
//	}
//	
//	public List<Tag> getTags(Long userId) {
//		return userRepository.findAllByOwnerId(userId);
//	}
	
//	public Tag addTag(Long userId, String tagValue) {
//		List<Tag> tags = getTags(userId);
//		boolean notExistTag = true;
//		
//		for (Tag t : tags) {
//			if (t.getValue().equals(tagValue)) {
//				notExistTag = false;
//				break;
//			}
//		}
//		
//		if (notExistTag) {
//			Tag tag = new Tag(userId, tagValue);
//			userRepository.save(tag);
//			return tag;
//		}
//		return null;
//	}

	/*
	 * User가 가지고 있는 Tag만을 삭제
	 */
	public User removeTag(ObjectId userId, ObjectId tagId) {
		User user = userRepository.findOne(userId);
		
		logger.debug("Before Size : " + user.getTags().size());
		
		List<Tag> tags = user.getTags();
		for (int i = 0, len = tags.size(); i < len; i++) {
			if (tagId.equals(tags.get(i).getId())) {
				tags.remove(i);
				user.setTags(tags);
				return user;
			}
		}
		
		logger.debug("After Size : " + user.getTags().size());
		return null;
	}
	
	public void updateProfileImage(ObjectId userId, MultipartFile profileImage) {
		User user = userRepository.findOne(userId);
		user.setEncodeProfileImage(Base64.encode(RaptozUtil.getBytes(profileImage)));
		userRepository.save(user);
	}
	
	private boolean isEqual(String pwd1, String pwd2) {
		return pwd1.trim().equals(pwd2.trim());
	}
}