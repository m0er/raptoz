package com.raptoz.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

	User findByEmailAndPassword(String email, String password);

}
