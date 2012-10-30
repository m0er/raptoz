package com.raptoz.post;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.raptoz.reply.Reply;
import com.raptoz.reply.ReplyRepository;
import com.raptoz.tag.Tag;
import com.raptoz.tag.TagRepository;
import com.raptoz.user.User;
import com.raptoz.user.UserRepository;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PostRepositoryTest {
	@Autowired MongoTemplate mongoTemplate;
	@Autowired TagRepository tagRepository;
	@Autowired PostRepository postRepository;
	@Autowired UserRepository userRepository;
	@Autowired ReplyRepository replyRepository;
	
	User user;
	Post post1, post2, post3;
	Tag tag1, tag2, tag3;
	Reply reply1, reply2, reply3;
	
	@Before
	public void setUp() {
		Tag userTag = new Tag("tag1");
		tagRepository.save(userTag);
		
		user = new User("testuser@raptoz.com", "test", "testuser", "");
		user.setTags(Arrays.asList(userTag));
		userRepository.save(user);
		
		post1 = new Post("title1", "description1", user);
		post2 = new Post("title2", "description2", user);
		post3 = new Post("title3", "description3", user);
		
		tag1 = new Tag("tag1");
		tag2 = new Tag("tag2");
		tag3 = new Tag("tag3");
		
		reply1 = new Reply("reply1", user);
		reply2 = new Reply("reply2", user);
		reply3 = new Reply("reply3", user);
	}
	
	@Test
	public void create() throws Exception {
		assertThat(postRepository, is(notNullValue()));
		assertThat(userRepository, is(notNullValue()));
	}
	
	@Test
	public void crud() throws Exception {
		// save tags
		tagRepository.save(Arrays.asList(tag1, tag2, tag3));

		// save posts
		long before = postRepository.count();
		
		post1.setTags(Arrays.asList(tag1, tag2, tag3));
		postRepository.save(Arrays.asList(post1, post2, post3));
		assertThat(postRepository.count(), is(before + 3));
		assertThat(postRepository.findOne(post1.getId()).getTags(), hasItems(tag1, tag2, tag3));
		
		log.info(postRepository.findAll().toString());
		
		// update post
		post1.setContent("asdf");
		post1.setTags(Arrays.asList(tag2));
		postRepository.save(post1);
		
		Post found = postRepository.findOne(post1.getId());
		assertThat(found.getContent(), is(post1.getContent()));
		assertThat(found.getTags(), not(hasItems(tag1, tag3)));
		
		// save replies
		replyRepository.save(Arrays.asList(reply1, reply2, reply3));
		post1.setReplyIds(Arrays.asList(reply1.getId(), reply2.getId(), reply3.getId()));
		postRepository.save(post1);
		
		found = postRepository.findOne(post1.getId());
		List<ObjectId> replyIdList = found.getReplyIds();
		assertThat(replyIdList.size(), is(3));
		
		// retrieve replies
		// same as: mongoTemplate.find(query(where("_id").in(replyIdList)), Reply.class);
		List<Reply> foundReplyList = replyRepository.findByIdIn(replyIdList);
		assertThat(foundReplyList, hasItems(reply1, reply2, reply3));
		
		log.info(replyRepository.findAll().toString());
	}
	
	@Test
	public void ObjectId안에_생성시간이_있다() throws Exception {
		postRepository.save(post1);
		// created 필드를 없애도 될 것 같긴 한데..
		assertThat(new Date(post1.getId().getTime()).toString(), is(notNullValue()));
	}
	
	@Test
	public void Tag_value로_검색() throws Exception {
		List<Tag> tags = Arrays.asList(tag1, tag2, tag3);
		tagRepository.save(tags);
		
		post1.setTags(tags);
		post1.setCreated(new Date());
		post1.setViewCount(0L);
		postRepository.save(post1);
		
		List<Post> postList = postRepository.findByTagsValue(tag1.getValue());
		
		assertThat(postList.get(0).getId(), is(post1.getId()));
	}
	
	@Test
	public void limit() throws Exception {
		postRepository.save(Arrays.asList(post1, post2, post3));
		
		// same as
		// List<Post> founds = mongoTemplate.find(Query.query(Criteria.where("writer._id").is(user.getId())).limit(1), Post.class);
		List<Post> founds = postRepository.findByWriterId(user.getId(), new PageRequest(0, 1));
		
		assertThat(founds.size(), is(1));
	}
	
	@After
	public void tearDown() {
		tagRepository.deleteAll();
		userRepository.deleteAll();
		postRepository.deleteAll();
		replyRepository.deleteAll();
	}
	
}
