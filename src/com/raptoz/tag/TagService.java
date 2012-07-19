package com.raptoz.tag;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private TagRepository tagRepository;
	@Autowired private MongoTemplate mongoTemplate;
	
	/**
	 * 있으면 Update or 없으면 Insert
	 * @param tag
	 * @return
	 */
	public Tag upsert(Tag tag) {
		String value = tag.getValue();
		mongoTemplate.upsert(query(where("value").is(value)), new Update().inc("count", 1L), Tag.class);
		
		Tag found = tagRepository.findByValue(value);
		
		logger.info(tag.getValue() + " count: " + found.getCount());
		
		return found;
	}
}