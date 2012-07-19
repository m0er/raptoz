package com.raptoz.tag;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class TagServiceTest {
	@Autowired TagService tagService;
	@Autowired TagRepository tagRepository;
	@Autowired MongoTemplate mongoTemplate;
	
	Tag tag1, tag2;
	
	static final String TAG_VALUE = "THIS_IS_SPARTA!!!";
	
	@Before
	public void setUp() throws Exception {
		tag1 = new Tag(TAG_VALUE);
		tag2 = new Tag(TAG_VALUE);
		
		mongoTemplate.findAndRemove(query(where("value").is(TAG_VALUE)), Tag.class);
		
		assertThat(tagRepository.findByValue(TAG_VALUE), is(nullValue()));
	}
	
	@Test
	public void countInc() throws Exception {
		tag1 = tagService.upsert(tag1);
		
		assertThat(tag1.getId(), is(notNullValue(ObjectId.class)));
		assertThat(tag1.getCount(), is(1L));
		
		tag2 = tagService.upsert(tag2);
		assertThat(tag2.getId(), is(tag1.getId()));
		assertThat(tag2.getCount(), is(2L));
	}
	
	@Test
	public void upsert_리스트가_null일때() throws Exception {
		List<Tag> tags = null;
		tagService.upsert(tags);
	}
	
	@After
	public void tearDown() throws Exception {
		mongoTemplate.findAndRemove(query(where("value").is(TAG_VALUE)), Tag.class);
	}

}
