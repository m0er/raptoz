package com.raptoz.post;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.raptoz.user.User;

@Service("postService")
public class PostService {
	@Autowired private PostRepository postRepository;
	
	public void create(User writer, Post post) {
		post.setWriter(writer);
		post.setCreated(new Date());
		postRepository.save(post);
	}

	public Post get(ObjectId id) {
		Post post = postRepository.findOne(id);
		return post;
	}
	
	public List<Post> getByTag(String tag) {
		List<Post> posts = postRepository.findByTagsValue(tag);
		return posts;
	}

	public Post getById(ObjectId id) {
		Post post = postRepository.findOne(id);
		return post;
	}

	public List<Post> getRecent(final int limit) {
		Pageable pageable = new PageRequest(0, limit, Direction.DESC, "created");
		return postRepository.findAll(pageable).getContent();
	}

}