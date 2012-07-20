package com.raptoz.post;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.raptoz.activity.Activity.Type;
import com.raptoz.activity.FootPrintable;
import com.raptoz.tag.Tag;
import com.raptoz.user.User;

@Document
public class Post implements FootPrintable {
	private ObjectId id;
	private String title;
	private Date created;
	private Long viewCount;
	private String content;

	private User writer;
	private List<Tag> tags;
	private List<ObjectId> replyIds;
	
	public Post() {
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

	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<ObjectId> getReplyIds() {
		return replyIds;
	}

	public void setReplyIds(List<ObjectId> replyIds) {
		this.replyIds = replyIds;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", created=" + created
				+ ", viewCount=" + viewCount + ", content=" + content
				+ ", writer=" + writer + ", tags=" + tags + ", replyIds="
				+ replyIds + "]";
	}

	@Override
	public User getOwner() {
		return writer;
	}

	@Override
	public Type getType() {
		return Type.POST;
	}

	@Override
	public void clear() {
		setCreated(null);
		setWriter(null);
	}

	@Override
	public String getContentString() {
		return "[" + title + "]" + " " + content;
	}
	
}
