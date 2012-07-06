package com.raptoz.post;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.raptoz.tag.Tag;
import com.raptoz.user.User;

@Document
public class Post {
	private ObjectId id;
	private String title;
	private Date created;
	private Long viewCount;
	private String content;

	private User writer;
	private List<Tag> tagList;
	private List<ObjectId> replyIdList;
	
	public Post() {
		this.created = new Date();
	}
	
	public Post(String title, String content, User writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public List<Tag> getTagList() {
		return tagList;
	}
	
	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public List<ObjectId> getReplyIdList() {
		return replyIdList;
	}

	public void setReplyIdList(List<ObjectId> replyIdList) {
		this.replyIdList = replyIdList;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", created=" + created
				+ ", viewCount=" + viewCount + ", content=" + content
				+ ", writer=" + writer + ", tagList=" + tagList
				+ ", replyIdList=" + replyIdList + "]";
	}
	
}
