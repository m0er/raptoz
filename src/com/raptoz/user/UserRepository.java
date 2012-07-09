package com.raptoz.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, ObjectId> {

	User findByEmailAndPassword(String email, String password);

	List<User> findByTagsValue(String value);
	
	@Query(value = "{ 'id' : ?0 }", fields = "{password: 0}")
	User findOneSimpleById(ObjectId id);
	
	@Query(value = "{ 'tags.value' : ?0 }", fields = "{password: 0}")
	List<User> findSimpleByTagsValue(String value);
	
}
