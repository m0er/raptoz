package com.raptoz.activity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity<? extends FootPrintable>, ObjectId> {

	List<Activity<? extends FootPrintable>> findByOwnerId(ObjectId id, Pageable Pageable);
	
}
