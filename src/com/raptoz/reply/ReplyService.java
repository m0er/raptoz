package com.raptoz.reply;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("replyService")
public class ReplyService {
	@Autowired ReplyRepository replyRepository;
	
	public void add(Reply tozParticipant) {
		replyRepository.save(tozParticipant);
	}

	public void delete(ObjectId id) {
		replyRepository.delete(id);
	}

	public List<Reply> get(ObjectId id) {
		List<Reply> replyList = replyRepository.findAllById(id);
		return replyList;
	}
}
