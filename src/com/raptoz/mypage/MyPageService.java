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
	
	public User update(ObjectId userId, PersonalInfo infos) {
		User user = userRepository.findOne(userId);
		String email = user.getEmail();
		String password = infos.getCurPwd();
		String newPwd = infos.getNewPwd();
		String confirmPwd = infos.getConfirmPwd();
		
		String nickname = infos.getNickname();
		String imgUrl = user.getEncodeProfileImage(); 
		
		if (password != null) {
			if (userRepository.findOneByEmailAndPassword(email, password) == null) {
				logger.info("failed identify password");
				return null;
			}
			
			if (newPwd.trim().length() == 0 || confirmPwd.trim().length() == 0 || !isEqual(newPwd, confirmPwd)) {
				logger.info("failed apply new password");
				return null;
			}
			userRepository.save(remakeUser(userId, email, newPwd, nickname, imgUrl));
		} else {
			userRepository.save(remakeUser(userId, email, user.getPassword(), nickname, imgUrl));	
		}
		
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
	
	public boolean isVerify(ObjectId userId, String password) {
		return !(userRepository.findOneByIdAndPassword(userId, password) == null);
	}
	
	public boolean isEqual(String pwd1, String pwd2) {
		return pwd1.trim().equals(pwd2.trim());
	}
	
	private User remakeUser(ObjectId userId, String email, String password, String nickname, String imgUrl) {
		userRepository.delete(userId);
		User user = new User(email, password, nickname, imgUrl);
		
		return user;
	}
}