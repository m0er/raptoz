package com.raptoz.user;

import java.util.List;

import org.apache.catalina.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.raptoz.post.Post;
import com.raptoz.util.RaptozUtil;

@Service("userService")
public class UserService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired private UserRepository userRepository;
	
	public void add(User user, MultipartFile profileImage) {
		String email = user.getEmail();
		user.setNickname(getNickname(email));
		user.setEncodeProfileImage(Base64.encode(RaptozUtil.getBytes(profileImage)));
		userRepository.save(user);
	}
	
//	public List<User> getByTagWithTagAndToz(String value) {
//		throw new UnsupportedOperationException();
//	}
	
	private List<Post> getRecentUserParticipantToz(Long userId) {
//		List<Reply> tozParticipantList = tozParticipantMapper.findAllByUsrId(userId);
//		List<Post> userParticipantTozList = new ArrayList<Post>();
//		
//		for (Reply tozParticipant : tozParticipantList) {
//			Post toz = tozMapper.findOne(tozParticipant.getTozId());
//			userParticipantTozList.add(toz);
//		}
//		
//		return userParticipantTozList;
		
		throw new UnsupportedOperationException();
	}

	public User login(User user) {
		User found = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (found != null) {
			logger.info("로그인 성공!: " + found.toString());
			return found;
		} else {
			logger.info("사용자를 찾을 수 없습니다: " + user.toString());
			return null;
		}
	}

	private String getNickname(String email) {
		return email.split("@")[0];
	}

}
