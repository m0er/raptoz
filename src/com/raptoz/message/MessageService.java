package com.raptoz.message;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired MessageRepository messageRepository;
	@Autowired MongoTemplate mongoTemplate;
	
	public void send(Message message) {
		messageRepository.save(message);
	}

	public long count(ObjectId id) {
		return mongoTemplate.count(Query.query(Criteria.where("to.id").is(id).andOperator(Criteria.where("read").is(false))), Message.class);
	}
	
}
