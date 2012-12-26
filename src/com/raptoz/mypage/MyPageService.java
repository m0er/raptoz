package com.raptoz.mypage;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.util.Base64;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.raptoz.post.Post;
import com.raptoz.post.PostRepository;
import com.raptoz.tag.Tag;
import com.raptoz.user.User;
import com.raptoz.user.UserRepository;
import com.raptoz.util.RaptozUtil;

@Slf4j
@Service("mypageService")
public class MyPageService {
	@Autowired private UserRepository userRepository;
	@Autowired private PostRepository postRepository;
	@Autowired private MongoTemplate mongoTemplate;
	
	public User update(ObjectId userId, MyPage mypage) {
		User user = userRepository.findOne(userId);
		user.setNickname(mypage.getNickname());
		user.setTags(mypage.getTags());
		
		userRepository.save(user);
		
		log.info("update success");
		
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
	
	public MyPageDto getInfo(ObjectId id) {
		User user = userRepository.findOneSimpleById(id);
		List<Post> posts = postRepository.findByWriterId(id, new PageRequest(0, 100, Direction.DESC, "created"));
		
		MyPageDto myPageDto = new MyPageDto();
		myPageDto.setPosts(posts);
		myPageDto.setUser(user);
		
		return myPageDto;
	}
}