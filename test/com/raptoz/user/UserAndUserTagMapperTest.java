package com.raptoz.user;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.apache.catalina.util.*;
import org.junit.*;
import org.junit.runner.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.raptoz.tag.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserAndUserTagMapperTest {
	@Autowired UserMapper userMapper;
	@Autowired UserTagMapper userTagMapper;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	User user;
	Tag tag; 
	
	@Before
	public void setUp() throws IOException {
		File dummyImage = new File("C:/Developer/workspace.raptoz/raptoz/web/image/50x50.gif");
		InputStream fis = new BufferedInputStream(new FileInputStream(dummyImage));
		byte[] buffer = new byte[4096];
		int result = fis.read(buffer);
		logger.info("read: " + result);
		
		String encodeDummyImage = Base64.encode(buffer);
		logger.info("Base64 encode: " + encodeDummyImage);
		
		user = new User("test1@raptoz.com", "test", "test1", encodeDummyImage);
		userMapper.save(user);
		assertThat(userMapper.count(), is(1L));
		assertThat(userMapper.exists(user.getId()), is(true));
	}
	
	@Test
	public void insertInitialTag() throws Exception {
		tag = new Tag(user.getId(), "Music");
		userTagMapper.save(tag);
		assertThat(userTagMapper.count(), is(1L));
		assertThat(userTagMapper.exists(tag.getId()), is(true));
		
		List<Tag> tagList = userTagMapper.findAllByOwnerId(user.getId());
		assertThat(tagList.size(), is(1));
		assertThat(tagList.get(0).getValue(), is("Music"));
	}
	
	@After
	public void tearDown() {
		userMapper.deleteAll();
		userTagMapper.deleteAll();
	}
}
