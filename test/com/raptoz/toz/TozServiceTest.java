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
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class TozServiceTest {
	@Autowired TozTagMapper tozTagMapper;
	@Autowired UserMapper userMapper;
	@Autowired TozMapper tozMapper;
	
	@Autowired TozService tozService;
	
	@Before
	public void setUp() {
		User questioner = new User("questioner@raptoz.com", "test", "questioner", "");
		userMapper.save(questioner);
		
		Toz toz1 = new Toz("title1", questioner.getId(), "description1");
		Toz toz2 = new Toz("title2", questioner.getId(), "description2");
		Toz toz3 = new Toz("title3", questioner.getId(), "description3");
		tozMapper.save(toz1);
		tozMapper.save(toz2);
		tozMapper.save(toz3);
		
		Tag coffee = new Tag("coffee");
		Tag waffle = new Tag("waffle");
		Tag tea = new Tag("tea");
		
		saveTags(toz1, coffee, waffle);
		saveTags(toz2, coffee, tea);
		saveTags(toz3, tea, waffle);
	}
	
	@Test
	public void getByTagWithAllTags() throws Exception {
		List<Toz> tozList = tozService.getByTagWithAllTags("coffee");
		assertThat(tozList.size(), is(2));
		assertThat(tozList.get(0).getTagList().size(), is(2));
		assertThat(tozList.get(1).getTagList().size(), is(2));
	}

	private void saveTags(Toz toz, Tag...tags) {
		for (Tag tag : tags) {
			tag.setOwnerId(toz.getId());
			tozTagMapper.save(tag);
		}
	}
	
	@After
	public void tearDown() {
		tozMapper.deleteAll();
		tozTagMapper.deleteAll();
		userMapper.deleteAll();
	}
}
