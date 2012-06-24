package com.raptoz.toz;

import java.util.*;

import com.raptoz.tag.*;
import com.raptoz.user.*;

public class Toz {
	private Long id;
	private String title;
	private Date createTime;
	private User questioner;
	private Long viewCount;
	private String description;
	private Long questionerId;
	
	private List<Tag> tagList;
	private List<TozParticipant> tozParticipantList;
	
	public Toz() {
	}
	
	public Toz(String title, Long questionerId, String description) {
		this.title = title;
		this.questionerId = questionerId;
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getQuestioner() {
		return questioner;
	}
	
	public void setQuestioner(User questioner) {
		this.questioner = questioner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getQuestionerId() {
		return questionerId;
	}

	public void setQuestionerId(Long questionerId) {
		this.questionerId = questionerId;
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

	public List<TozParticipant> getTozParticipantList() {
		return tozParticipantList;
	}

	public void setTozParticipantList(List<TozParticipant> tozParticipantList) {
		this.tozParticipantList = tozParticipantList;
	}

	@Override
	public String toString() {
		return "Toz [id=" + id + ", title=" + title + ", createTime="
				+ createTime + ", questioner=" + questioner + ", viewCount="
				+ viewCount + ", description=" + description
				+ ", questionerId=" + questionerId + ", tagList=" + tagList
				+ ", tozParticipantList=" + tozParticipantList + "]";
	}
	
}
