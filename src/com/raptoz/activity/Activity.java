package com.raptoz.activity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.raptoz.user.User;

@Document
public class Activity<T extends FootPrintable> {
	private ObjectId id;
	private Type type;
	private User owner;
	private T content;
	private Date created;
	private String contentString;
	
	public Activity() {
	}
	
	public Activity(T content) {
		setContent(content);
	}
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
		this.owner = content.getOwner();
		this.created = content.getCreated();
		this.type = content.getType();
		this.contentString = content.getContentString();
		content.clear();
	}
	
	public Date getCreated() {
		return created;
	}
	
	public String getContentString() {
		return contentString;
	}
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", type=" + type + ", owner=" + owner
				+ ", content=" + content + ", created=" + created
				+ ", contentString=" + contentString + "]";
	}
	
	public enum Type {
		POST, REPLY
	}
	
}
