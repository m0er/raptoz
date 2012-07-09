package com.raptoz.reply;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raptoz.user.User;

@Service("replyService")
public class ReplyService {
	@Autowired ReplyRepository replyRepository;
	
	public Reply add(User user, Reply reply) {
		reply.setWriter(user);
		reply.setCreated(new Date());
		
		return replyRepository.save(reply);
	}

	public void delete(ObjectId id) {
		replyRepository.delete(id);
	}

	public List<Reply> get(ObjectId id) {
		List<Reply> replyList = replyRepository.findAllByPostId(id);
		return replyList;
	}
}
