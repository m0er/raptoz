package com.raptoz.tag;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private TagRepository tagRepository;
	
	public Tag addTag(Tag tag) {
		Tag insertedTag = tagRepository.save(tag);
		
		List<Tag> tags = tagRepository.findAll();
		if (tags.size() != 0) {
			for (Tag t : tags) {
				if (insertedTag.getId().equals(t.getId())) continue;
				
				if (tag.getId().equals(t.getId())) {
					Long cnt = t.getCount();
					insertedTag.setCount(cnt + 1);
					break;
				}
			}
		} else {
			insertedTag.setCount((long)1);
		}
		return tagRepository.save(insertedTag);
	}
}