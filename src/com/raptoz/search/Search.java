package com.raptoz.search;

import java.util.*;

import com.raptoz.toz.*;
import com.raptoz.user.*;

public class Search {
	private List<User> userList;
	private List<Toz> tozList;
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Toz> getTozList() {
		return tozList;
	}

	public void setTozList(List<Toz> tozList) {
		this.tozList = tozList;
	}

	@Override
	public String toString() {
		return "Search [userList=" + userList + ", tozList=" + tozList + "]";
	}
}
