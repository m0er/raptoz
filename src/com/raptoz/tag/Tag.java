package com.raptoz.tag;

public class Tag {
	private Long id;
	private Long ownerId;
	private String value;
	
	public Tag() {
	}
	
	public Tag(String value) {
		this.value = value;
	}

	public Tag(Long ownerId, String value) {
		this(value);
		this.ownerId = ownerId;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", ownerId=" + ownerId + ", value=" + value
				+ "]";
	}
}
