package com.raptoz.mypage;


import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raptoz.user.User;
import com.raptoz.user.UserRepository;

@Service("mypageService")
public class MyPageService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Autowired UserRepository userRepository;
//	
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
//	
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
//	
//	public void deleteTag(Long tagId) {
//		userRepository.deleteById(tagId);
//	}
//	
//	public void updateProfileImage(Long userId, MultipartFile profileImage) {
//		userMapper.updateProfileImage(Base64.encode(RaptozUtil.getBytes(profileImage)), userId);
//	}
	
	private boolean isEqual(String pwd1, String pwd2) {
		return pwd1.trim().equals(pwd2.trim());
	}
}