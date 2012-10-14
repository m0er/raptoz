package com.raptoz.message;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, ObjectId>{
	
	List<Message> findByToId(Object id);
	
}
