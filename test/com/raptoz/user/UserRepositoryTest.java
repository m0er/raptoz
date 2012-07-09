package com.raptoz.user;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.raptoz.tag.Tag;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class UserRepositoryTest {
	@Autowired UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	User user1;
	Tag tag1, tag2, tag3;
	
	@Before
	public void setUp() throws Exception {
		user1 = new User("user1@raptoz.com", "test", "user1", "");
		
		tag1 = new Tag("tag1");
		tag2 = new Tag("tag2");
		tag3 = new Tag("tag3");
		
		user1.setTags(Arrays.asList(tag1, tag2, tag3));
		
		userRepository.save(user1);
	}
	
	
	@Test
	public void findOneSimpleById() throws Exception {
		User found = userRepository.findOneSimpleById(user1.getId());
		
		logger.info(found.toString());
		
		assertThat(found.getPassword(), is(nullValue()));
	}
	
	@Test
	public void findSimpleByTagsValue() throws Exception {
		List<User> users = userRepository.findSimpleByTagsValue("tag1");
		
		logger.info(users.toString());
		
		assertThat(users, is(notNullValue()));
		assertThat(users.get(0).getId(), is(user1.getId()));
	}
	
	@After
	public void tearDown() {
		userRepository.deleteAll();
	}
}