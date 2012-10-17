package com.raptoz.message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.raptoz.user.User;

public class Message {
	private boolean read;
	private String content;
	private User from;
	private User to;
	private Date sent;
	private Date received;
	private String sentString;
	
	public Message() {
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public Date getSent() {
		return sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
		this.sentString = df.format(new Date());
	}
	
	public String getSentString() {
		return sentString;
	}
	
	public Date getReceived() {
		return received;
	}

	public void setReceived(Date received) {
		this.received = received;
	}

	@Override
	public String toString() {
		return "Message [read=" + read + ", content=" + content + ", from="
				+ from + ", to=" + to + ", sent=" + sent + ", received="
				+ received + "]";
	}
}
