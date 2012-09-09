package com.raptoz.reply;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.raptoz.user.User;

@Document
public class Reply {
	private ObjectId id;
	private String idString;
	
	private Date created;
	private String content;
	private ObjectId postId;
	
	private User writer;
	
	public Reply() {
	}
	
	public Reply(String content, User writer) {
		this.content = content;
		this.writer = writer;
	}

	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
		this.idString = id.toString();
	}

	public String getIdString() {
		if (idString == null && id != null)
			return id.toString();
		return idString;
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

	public ObjectId getPostId() {
		return postId;
	}

	public void setPostId(ObjectId postId) {
		this.postId = postId;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", created=" + created + ", content="
				+ content + ", postId=" + postId + ", writer=" + writer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reply other = (Reply) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
