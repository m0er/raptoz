package com.raptoz.security;


public enum Role {
	ADMIN("admin", 1), COMMUNITY("community", 2);
	
	private String name;
	private int value;
	
	private Role(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
}
