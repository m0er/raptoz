package com.raptoz.user;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.raptoz.tag.Tag;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class UserRepositoryTest {
	@Autowired UserRepository userRepository;
	@Autowired MongoTemplate mongoTemplate;
	
	User user0, user1;
	Tag tag1, tag2, tag3;
	
	@Before
	public void setUp() throws Exception {
		user0 = new User("user0@raptoz.com", "test", "user0", "");
		user1 = new User("user1@raptoz.com", "test", "user1", "");
		
		tag1 = new Tag("tag1");
		tag2 = new Tag("tag2");
		tag3 = new Tag("tag3");
		
		user1.setTags(Arrays.asList(tag1, tag2, tag3));
		
		userRepository.save(Arrays.asList(user0, user1));
	}
	
	
	@Test
	public void findOneSimpleById() throws Exception {
		User found = userRepository.findOneSimpleById(user1.getId());
		
		log.info(found.toString());
		
		assertThat(found.getPassword(), is(nullValue()));
	}
	
	@Test
	public void findSimpleByTagsValue() throws Exception {
		List<User> users = userRepository.findSimpleByTagsValue("tag1");
		
		log.info(users.toString());
		
		assertThat(users, is(notNullValue()));
		assertThat(users.get(0).getId(), is(user1.getId()));
	}
	
	@Test
	public void findAllSimple() throws Exception {
		List<User> users = userRepository.findAllSimple();
		
		for (User user : users)
			assertThat(user.getPassword(), is(nullValue()));
	}
	
	@Test
	public void findByEmailAndPassword() throws Exception {
		User found = userRepository.findOneByEmailAndPassword("user1@raptoz.com", "test");
		
		assertThat(user1.getId(), is(found.getId()));
		assertThat(user1.getEmail(), is(found.getEmail()));
	}
	
	@After
	public void tearDown() {
		userRepository.deleteAll();
	}
}
