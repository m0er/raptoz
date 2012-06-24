package com.raptoz.mypage;


import java.util.*;

import org.apache.catalina.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.raptoz.tag.*;
import com.raptoz.user.*;
import com.raptoz.util.RaptozUtil;

@Service("mypageService")
public class MyPageService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserMapper userMapper;
	@Autowired UserTagMapper userTagMapper;
	
	public User getUser(Long userId) {
		return userMapper.findOne(userId);
	}
		
	public User updateUser(Long userId, PersonalInfo personalInfo) {
		if (userMapper.isVerifyPassword(getUser(userId).getEmail(), personalInfo.getCurPwd())) {
			logger.info("failed identify password");
			return null;
		}
		
		String newPwd = personalInfo.getNewPwd();
		String confirmPwd = personalInfo.getConfirmPwd();
		if (newPwd.trim().length() == 0 || confirmPwd.trim().length() == 0 || !isEqual(newPwd, confirmPwd)) {
			logger.info("failed apply new password");
			return null;
		}
		
		userMapper.deleteById(userId);
		User user = new User(personalInfo.getEmail(), newPwd, personalInfo.getNickname(), "");
		userMapper.save(user);
		logger.info("update success");
		
		return user;
	}
	
	public List<Tag> getTags(Long userId) {
		return userTagMapper.findAllByOwnerId(userId);
	}
	
	public Tag addTag(Long userId, String tagValue) {
		List<Tag> tags = getTags(userId);
		boolean notExistTag = true;
		
		for (Tag t : tags) {
			if (t.getValue().equals(tagValue)) {
				notExistTag = false;
				break;
			}
		}
		
		if (notExistTag) {
			Tag tag = new Tag(userId, tagValue);
			userTagMapper.save(tag);
			return tag;
		}
		return null;
	}
	
	public void deleteTag(Long tagId) {
		userTagMapper.deleteById(tagId);
	}
	
	public void updateProfileImage(Long userId, MultipartFile profileImage) {
		userMapper.updateProfileImage(Base64.encode(RaptozUtil.getBytes(profileImage)), userId);
	}
	
	private boolean isEqual(String pwd1, String pwd2) {
		return pwd1.trim().equals(pwd2.trim());
	}
}