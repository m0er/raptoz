package com.raptoz.search;

import java.util.*;

import com.raptoz.post.*;
import com.raptoz.user.*;

public class Search {
	private List<User> userList;
	private List<Post> postList;
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	@Override
	public String toString() {
		return "Search [userList=" + userList + ", postList=" + postList + "]";
	}
}
