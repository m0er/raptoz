package com.raptoz.participant;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.raptoz.toz.*;
import com.raptoz.user.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class TozParticipantMapperTest {
	@Autowired UserMapper userMapper;
	@Autowired TozMapper tozMapper;
	@Autowired TozParticipantMapper tozParticipantMapper;
	
	Toz toz;
	List<TozParticipant> tozParticipantList;
	
	@Before
	public void setUp() {
		User questioner = new User("questioner@raptoz.com", "test", "test1", "");
		userMapper.save(questioner);
		toz = new Toz("test toz1", questioner.getId(), "How do you think about...");
		tozMapper.save(toz);
		
		User participant1 = new User("participant1@raptoz.com", "password", "participant1", "");
		User participant2 = new User("participant2@raptoz.com", "password", "participant2", "");
		User participant3 = new User("participant3@raptoz.com", "password", "participant3", "");
		userMapper.save(participant1);
		userMapper.save(participant2);
		userMapper.save(participant3);
		
		tozParticipantList = Arrays.asList(
				new TozParticipant(toz.getId(), participant1.getId(), "I think..."),
				new TozParticipant(toz.getId(), participant2.getId(), "I think..."),
				new TozParticipant(toz.getId(), participant3.getId(), "I think..."));
	}
	
	@Test
	public void crud() throws Exception {
		TozParticipant tozParticipant = tozParticipantList.get(0);
		tozParticipantMapper.save(tozParticipant);
		
		assertThat(tozParticipantMapper.count(), is(1L));
		assertThat(tozParticipantMapper.countByTozId(toz.getId()), is(1L));
		assertThat(tozParticipantMapper.exists(tozParticipant.getId()), is(true));
		assertThat(tozParticipantMapper.findOne(tozParticipant.getId()), is(TozParticipant.class));
		
		tozParticipantMapper.delete(tozParticipant);
		assertThat(tozParticipantMapper.count(), is(0L));
		assertThat(tozParticipantMapper.exists(tozParticipant.getId()), is(false));
	}
	
	@Test
	public void findAllByTozId() throws Exception {
		for (TozParticipant tozParticipant : tozParticipantList)
			tozParticipantMapper.save(tozParticipant);
		
		List<TozParticipant> foundTozParticipants = tozParticipantMapper.findAllByTozId(toz.getId());
		assertThat(foundTozParticipants.size(), is(3));
	}
	
	@After
	public void tearDown() {
		tozMapper.deleteAll();
		userMapper.deleteAll();
		tozParticipantMapper.deleteAll();
	}
}
