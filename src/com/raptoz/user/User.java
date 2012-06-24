package com.raptoz.user;

import java.util.*;

import com.raptoz.tag.*;
import com.raptoz.toz.*;


public class User {
	private Long id;
	private String email;
	private String password;
	private String nickname;
	private String encodeProfileImage;
	private List<Tag> userTagList;
	private List<Toz> recentParticipantTozList;
	
	public User() {
	}
	
	public User(String email, String password, String nickname, String encodeProfileImage) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.encodeProfileImage = encodeProfileImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<Tag> getUserTagList() {
		return userTagList;
	}

	public void setUserTagList(List<Tag> userTagList) {
		this.userTagList = userTagList;
	}

	public List<Toz> getRecentParticipantTozList() {
		return recentParticipantTozList;
	}

	public void setRecentParticipantTozList(List<Toz> recentParticipantTozList) {
		this.recentParticipantTozList = recentParticipantTozList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", nickname=" + nickname + ", encodeProfileImage="
				+ encodeProfileImage + ", userTagList=" + userTagList
				+ ", recentParticipantTozList=" + recentParticipantTozList
				+ "]";
	}
	
}
