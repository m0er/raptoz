package com.raptoz.user;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.raptoz.activity.Activity;
import com.raptoz.activity.FootPrintable;
import com.raptoz.tag.Tag;

@Document
public class User {
	private ObjectId id;
	private Date joined;
	private String email;
	private String password;
	private String nickname;
	private String encodeProfileImage;
	
	private List<Tag> tags;
	private List<Activity<? extends FootPrintable>> activities;
	
	public User() {
	}
	
	public User(String email, String password, String nickname, String encodeProfileImage) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.encodeProfileImage = encodeProfileImage;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEncodeProfileImage() {
		return encodeProfileImage;
	}

	public void setEncodeProfileImage(String encodeProfileImage) {
		this.encodeProfileImage = encodeProfileImage;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		if (tags == null)
			tags = new ArrayList<>();
		
		this.tags = tags;
	}
	
	public List<Activity<? extends FootPrintable>> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity<? extends FootPrintable>> activities) {
		if (activities == null)
			activities = new ArrayList<>();
		this.activities = activities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", joined=" + joined + ", email=" + email
				+ ", password=" + password + ", nickname=" + nickname
				+ ", encodeProfileImage=" + encodeProfileImage + ", tags="
				+ tags + ", activities=" + activities + "]";
	}
	
}
