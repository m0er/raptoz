package com.raptoz.user;

import java.util.Date;
import java.util.List;

import org.apache.catalina.util.Base64;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.raptoz.util.RaptozUtil;

@Service("userService")
public class UserService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired private UserRepository userRepository;
	
	public void add(User user, MultipartFile profileImage) {
		user.setJoined(new Date());
		
		String email = user.getEmail();
		user.setNickname(getNickname(email));
		
		user.setEncodeProfileImage(Base64.encode(RaptozUtil.getBytes(profileImage)));
		
		userRepository.save(user);
	}
	
	public User login(User user) {
		User found = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (found != null) {
			logger.info("로그인 성공!: " + found.toString());
			return userRepository.findOneSimpleById(found.getId());
		} else {
			logger.info("사용자를 찾을 수 없습니다: " + user.toString());
			return null;
		}
	}

	private String getNickname(String email) {
		return email.split("@")[0];
	}

	public List<User> getByTag(String term) {
		List<User> users = userRepository.findSimpleByTagsValue(term);
		return users;
	}
	
	public User getById(ObjectId id) {
		User user = userRepository.findOne(id);
		return user;
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}
}
