package com.raptoz.mypage;

public class PersonalInfo {
	private String email;
	private String nickname;
	private String curPwd;
	private String newPwd;
	private String confirmPwd;

	public PersonalInfo() {}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCurPwd() {
		return curPwd;
	}
	public void setCurPwd(String curPwd) {
		this.curPwd = curPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
}