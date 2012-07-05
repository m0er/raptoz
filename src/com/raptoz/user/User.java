package com.raptoz.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.raptoz.tag.Tag;

public class User {
	private ObjectId id;
	private String email;
	private String password;
	private String nickname;
	private String encodeProfileImage;
	
	private List<Tag> tagList;
	
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

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", nickname=" + nickname + ", encodeProfileImage="
				+ encodeProfileImage + ", tagList=" + tagList + "]";
	}
	
}
