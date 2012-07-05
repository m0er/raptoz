package com.raptoz.post;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raptoz.user.User;

@Service("postService")
public class PostService {
	@Autowired PostRepository postRepository;
	
	public void create(User writer, Post post) {
		post.setWriter(writer);
		postRepository.save(post);
	}

	public Post get(ObjectId id) {
		Post post = postRepository.findOne(id);
		return post;
	}

}
