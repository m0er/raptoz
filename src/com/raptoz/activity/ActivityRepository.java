package com.raptoz.activity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity<? extends FootPrintable>, ObjectId> {
	
}
