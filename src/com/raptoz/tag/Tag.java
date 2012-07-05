package com.raptoz.tag;

import org.bson.types.ObjectId;

public class Tag {
	private ObjectId id;
	private String value;
	
	public Tag() {
	}
	
	public Tag(String value) {
		this.value = value;
	}

	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", value=" + value + "]";
	}
}
