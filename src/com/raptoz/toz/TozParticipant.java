package com.raptoz.toz;

import java.util.*;

import com.raptoz.user.*;

public class TozParticipant {
	private Long id;
	private Long tozId;
	private Long userId;
	private Date joinDate;
	private String comment;
	private User participant;
	
	public TozParticipant() {
	}
	
	public TozParticipant(Long tozId, Long userId, String comment) {
		this.tozId = tozId;
		this.userId = userId;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getTozId() {
		return tozId;
	}

	public void setTozId(Long tozId) {
		this.tozId = tozId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	@Override
	public String toString() {
		return "TozParticipant [id=" + id + ", tozId=" + tozId + ", userId="
				+ userId + ", joinDate=" + joinDate + ", comment=" + comment
				+ ", participant=" + participant + "]";
	}
	
}
