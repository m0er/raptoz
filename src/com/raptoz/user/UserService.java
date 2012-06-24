package com.raptoz.user;

import java.util.*;

import org.apache.catalina.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.raptoz.participant.*;
import com.raptoz.tag.*;
import com.raptoz.toz.*;
import com.raptoz.util.*;

@Service("userService")
public class UserService {
	@Autowired TozMapper tozMapper;
	@Autowired UserMapper userMapper;
	@Autowired UserTagMapper userTagMapper;
	@Autowired TozParticipantMapper tozParticipantMapper;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);

	public void add(User user, MultipartFile profileImage) {
		String email = user.getEmail();
		user.setNickname(getNickname(email));
		user.setEncodeProfileImage(Base64.encode(RaptozUtil.getBytes(profileImage)));
		userMapper.save(user);
	}
	
	public List<User> getByTagWithTagAndToz(String value) {
		List<Tag> foundTagList = userTagMapper.findAllByValue(value);
		
		List<User> userList = new ArrayList<User>();
		for (Tag foundTag : foundTagList) {
			Long ownerId = foundTag.getOwnerId();
			User user = userMapper.findOne(ownerId);
			
			List<Tag> userTagList = userTagMapper.findAllByOwnerId(ownerId);
			user.setUserTagList(userTagList);
			
			List<Toz> recentUserParticipantTozList = getRecentUserParticipantToz(ownerId);
			user.setRecentParticipantTozList(recentUserParticipantTozList);
			userList.add(user);
		}
		
		return userList;
	}

	private List<Toz> getRecentUserParticipantToz(Long userId) {
		List<TozParticipant> tozParticipantList = tozParticipantMapper.findAllByUsrId(userId);
		List<Toz> userParticipantTozList = new ArrayList<Toz>();
		
		for (TozParticipant tozParticipant : tozParticipantList) {
			Toz toz = tozMapper.findOne(tozParticipant.getTozId());
			userParticipantTozList.add(toz);
		}
		
		return userParticipantTozList;
	}

	public User login(User user) {
		User foundUser = userMapper.findOneByEmailAndPassword(user.getEmail(), user.getPassword());
		if (foundUser != null) {
			logger.info("로그인 성공!: " + foundUser.getNickname());
			return foundUser;
		} else {
			logger.info("사용자를 찾을 수 없습니다: " + user.getEmail());
			return null;
		}
	}

	private String getNickname(String email) {
		return email.split("@")[0];
	}

}
