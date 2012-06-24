package com.raptoz.toz;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.raptoz.tag.*;
import com.raptoz.user.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TozAndTozTagMapperTest {
	@Autowired TozTagMapper tozTagMapper;
	@Autowired UserMapper userMapper;
	@Autowired TozMapper tozMapper;
	
	Toz toz;
	Tag tag;
	User user;
	
	@Before
	public void setUp() throws Exception {
		user = new User("test1@raptoz.com", "test", "test1", "");
		userMapper.save(user);
		
		toz = new Toz("test toz1", user.getId(), "What?");
		tozMapper.save(toz);
		assertThat(tozMapper.count(), is(1L));
		assertThat(tozMapper.exists(toz.getId()), is(true));
	}
	
	@Test
	public void insert() throws Exception {
		tag = new Tag(toz.getId(), "Java");
		tozTagMapper.save(tag);
		assertThat(tozTagMapper.count(), is(1L));
		assertThat(tozTagMapper.exists(tag.getId()), is(true));
		
		List<Tag> tozTagList = tozTagMapper.findAllByOwnerId(toz.getId());
		assertThat(tozTagList.size(), is(1));
		assertThat(tozTagList.get(0).getValue(), is("Java"));
	}

	@After
	public void tearDown() throws Exception {
		userMapper.deleteAll();
		tozMapper.deleteAll();
		tozTagMapper.deleteAll();
	}

}
