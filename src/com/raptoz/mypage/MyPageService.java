package com.raptoz.mypage;

import java.util.List;

import org.apache.catalina.util.Base64;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
	@Autowired MongoTemplate mongoTemplate;
	
	public User updateUser(ObjectId userId, PersonalInfo personalInfo) {
		User user = userRepository.findOne(userId);
		
		if (userRepository.findOneByEmailAndPassword(user.getEmail(), personalInfo.getCurPwd()) == null) {
			logger.info("failed identify password");
			return null;
		}
		
		String newPwd = personalInfo.getNewPwd();
		String confirmPwd = personalInfo.getConfirmPwd();
		if (newPwd.trim().length() == 0 || confirmPwd.trim().length() == 0 || !isEqual(newPwd, confirmPwd)) {
			logger.info("failed apply new password");
			return null;
		}
		
		userRepository.delete(userId);
		String imgUrl = user.getEncodeProfileImage();
		user = new User(personalInfo.getEmail(), newPwd, personalInfo.getNickname(), imgUrl);
		userRepository.save(user);
		logger.info("update success");
		
		return user;
	}

	public Tag removeTag(ObjectId userId, ObjectId tagId) {
		User user = userRepository.findOne(userId);
		List<Tag> tags = user.getTags();
		for (int i = 0, len = tags.size(); i < len; i++) {
			if (tagId.equals(tags.get(i).getId())) {
				Tag tag = tags.remove(i);
				user.setTags(tags);
				userRepository.save(user);
				return tag;
			}
		}
		/*
		 * ToDo
		 * 
		 * 반복 제어문 -> mongoTemplate으로 수정  
		 * 
		 */
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