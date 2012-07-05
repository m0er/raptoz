package com.raptoz.reply;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReplyRepository extends MongoRepository<Reply, ObjectId> {
	
	List<Reply> findAllById(ObjectId id);
	
	List<Reply> findByIdIn(Iterable<? extends ObjectId> entities);
	
}
