package com.raptoz.search;

import java.util.List;

import com.raptoz.activity.Activity;
import com.raptoz.activity.FootPrintable;
import com.raptoz.post.Post;
import com.raptoz.user.User;

public class Search {
	private List<User> users;
	private List<Post> posts;
	private List<Activity<? extends FootPrintable>> activities;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Activity<? extends FootPrintable>> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity<? extends FootPrintable>> activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		return "Search [users=" + users + ", posts=" + posts + ", activities="
				+ activities + "]";
	}

}
