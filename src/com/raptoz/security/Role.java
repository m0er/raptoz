package com.raptoz.security;


public enum Role {
	ADMIN("ROLE_ADMIN", 1), USER("ROLE_USER", 2), COMMUNITY("ROLE_COMMUNITY", 3);
	
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
