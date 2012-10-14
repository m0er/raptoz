package com.raptoz.tag;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
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

	public void upsert(List<Tag> tags) {
		if (tags == null)  {
			logger.debug("TagService.upsert(List<Tag>) :: " + "태그 리스트가 null 입니다");
			return;
		}
		
		for (Tag tag : tags) {
			upsert(tag);
		}
	}
	
	public void delete(Tag tag) {
		String value = tag.getValue();
		Tag found = tagRepository.findByValue(value);
		
		if (found.getCount() > 0) {
			mongoTemplate.upsert(query(where("value").is(value)), new Update().inc("count", -1L), Tag.class);
		} else {
			tagRepository.delete(tag);
		}
	}

	public List<Tag> listByTerm(String term, int limit) {
		return mongoTemplate.find(Query.query(Criteria.where("value").regex("^" + term + ".*")).limit(limit), Tag.class);
	}
}