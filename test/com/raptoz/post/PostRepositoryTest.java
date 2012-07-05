package com.raptoz.post;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.bson.types.ObjectId;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.raptoz.reply.Reply;
import com.raptoz.reply.ReplyRepository;
import com.raptoz.user.User;
import com.raptoz.user.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PostRepositoryTest {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired PostRepository postRepository;
	@Autowired UserRepository userRepository;
	@Autowired ReplyRepository replyRepository;
	@Autowired MongoTemplate mongoTemplate;
	
	User user;
	Post post1, post2, post3;
	Reply reply1, reply2, reply3;
	
	@Before
	public void setUp() {
		user = new User("testuser@raptoz.com", "test", "testuser", "");
		userRepository.save(user);
		user.setPassword(null);
		
		post1 = new Post("title1", "description1", user);
		post2 = new Post("title2", "description2", user);
		post3 = new Post("title3", "description3", user);
		
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
		// save posts
		long before = postRepository.count();
		postRepository.save(Arrays.asList(post1, post2, post3));
		assertThat(postRepository.count(), is(before + 3));
		
		// update post
		post1.setContent("asdf");
		postRepository.save(post1);
		
		Post found = postRepository.findOne(post1.getId());
		assertThat(found.getContent(), is(post1.getContent()));
		
		// save replies
		replyRepository.save(Arrays.asList(reply1, reply2, reply3));
		post1.setReplyIdList(Arrays.asList(reply1.getId(), reply2.getId(), reply3.getId()));
		postRepository.save(post1);
		
		found = postRepository.findOne(post1.getId());
		List<ObjectId> replyIdList = found.getReplyIdList();
		assertThat(replyIdList.size(), is(3));
		
		// retrieve replies
		// same as: mongoTemplate.find(query(where("_id").in(replyIdList)), Reply.class);
		List<Reply> foundReplyList = replyRepository.findByIdIn(replyIdList);
		assertThat(foundReplyList, hasItems(reply1, reply2, reply3));
	}
	
	@Test @Ignore
	public void ObjectId_생성시간이_있다() throws Exception {
		postRepository.save(post1);
		assertThat(new Date(post1.getId().getTime()).toString(), is(notNullValue()));
	}
	
	@After
	public void tearDown() {
		userRepository.deleteAll();
		postRepository.deleteAll();
		replyRepository.deleteAll();
	}
	
}
