package com.raptoz.search;

import java.util.List;

import lombok.Data;

import com.raptoz.post.Post;
import com.raptoz.user.User;

@Data
public class Search {
	private List<User> users;
	private List<Post> posts;	
}