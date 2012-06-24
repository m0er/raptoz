package com.raptoz.user;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.raptoz.tag.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class UserServiceTest {
	@Autowired UserMapper userMapper;
	@Autowired UserTagMapper userTagMapper;
	
	@Autowired UserService userService;
	
	@Before
	public void setUp() {
		User user1 = new User("match1@raptoz.com", "test", "match1", "");
		User user2 = new User("match2@raptoz.com", "test", "match2", "");
		User user3 = new User("unmatch@raptoz.com", "test", "unmatch", "");
		userMapper.save(user1);
		userMapper.save(user2);
		userMapper.save(user3);
		
		Tag coffee = new Tag("coffee");
		Tag waffle = new Tag("waffle");
		Tag apple = new Tag("apple");
		
		saveTagByUsers(coffee, user1, user2);
		saveTagByUsers(waffle, user1);
		saveTagByUsers(apple, user2, user3);
	}
	
	private void saveTagByUsers(Tag tag, User... users) {
		for (User user : users) {
			tag.setOwnerId(user.getId());
			userTagMapper.save(tag);
		}
	}
	
	@Test
		public void getByTagWithTagAndToz() throws Exception {
			List<User> userList = userService.getByTagWithTagAndToz("coffee");
			assertThat(userList.size(), is(2));
			assertThat(userList.get(0).getUserTagList().size(), is(2));
			assertThat(userList.get(1).getUserTagList().size(), is(2));
		}
	
	@After
	public void tearDown() {
		userTagMapper.deleteAll();
		userMapper.deleteAll();
	}
}
