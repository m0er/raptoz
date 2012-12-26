package com.raptoz.mypage;

import java.util.List;

import com.raptoz.tag.Tag;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyPage {
	private String email;
	private String nickname;
	private List<Tag> tags;
}