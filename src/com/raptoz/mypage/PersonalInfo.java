package com.raptoz.mypage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalInfo {
	private String email;
	private String nickname;
	private String curPwd;
	private String newPwd;
	private String confirmPwd;
}