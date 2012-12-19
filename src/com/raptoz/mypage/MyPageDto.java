package com.raptoz.mypage;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.raptoz.post.Post;
import com.raptoz.user.User;

@Data
@NoArgsConstructor
public class MyPageDto {
	private List<Post> posts;
	private User user;
}
