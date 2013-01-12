package com.raptoz.post;

import java.util.List;

import com.raptoz.reply.Reply;

import lombok.Data;

@Data
public class PostAndRepliesDto {
	private Post post;
	private List<Reply> replies;
}
