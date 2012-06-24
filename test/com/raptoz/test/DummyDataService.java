package com.raptoz.test;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.raptoz.participant.*;
import com.raptoz.tag.*;
import com.raptoz.toz.*;
import com.raptoz.user.*;

@Service("dummyDataService")
public class DummyDataService {
	@Autowired UserMapper userMapper;
	@Autowired UserTagMapper userTagMapper;
	@Autowired TozMapper tozMapper;
	@Autowired TozTagMapper tozTagMapper;
	@Autowired TozParticipantMapper tozParticipantMapper;
	
	Random random = new Random(System.currentTimeMillis());
	List<String> dummyTags = Arrays.asList(
			"coffee", "waffle", "java", "apple", "macbook", "web");
	String dummyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur mattis adipiscing nunc sit amet consectetur. Donec vitae molestie neque. Aliquam fringilla velit ut massa accumsan sed lacinia metus aliquet. Sed porttitor pellentesque mi, sed placerat arcu vehicula vitae. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec ornare congue ligula ut placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc interdum, mi vel varius faucibus, nibh justo fermentum nisi, vitae tincidunt neque velit sit amet nunc. Donec vulputate augue nec leo dignissim a fermentum purus auctor. Nam feugiat ultricies orci, tempor condimentum libero viverra et. Mauris massa enim, sollicitudin id varius at, egestas eu erat.";
	String[] dummyWords = dummyText.split(" ");
	
	public void create() {
		userMapper.save(new User("admin@raptoz.com", "test", "admin", ""));
		
		for (int count = 0; count < 100; count++) {
			User user = createDummyUserWithTags(count);
			createDummyTozWithTags(user);
		}
		addParticipants();
	}

	private void addParticipants() {
		List<User> userList = userMapper.findAll();
		List<Toz> tozList = tozMapper.findAll();
		
		for (int i = 0; i < 200; i++) {
			Toz randomToz = tozList.get(random.nextInt(tozList.size()));
			Long randomUserId = userList.get(random.nextInt(userList.size())).getId();
			Long randomTozId = randomToz.getId();
			
			if (isOwner(randomToz, randomUserId))
				tozParticipantMapper.save(new TozParticipant(randomTozId, randomUserId, "my toz..." + i));
			else
				tozParticipantMapper.save(new TozParticipant(randomTozId, randomUserId, generateDummyComment() + i));
		}
	}
	
	private String generateDummyComment() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < random.nextInt(30); i++)
			builder.append(dummyWords[random.nextInt(dummyWords.length)]);
		return builder.toString();
	}

	private boolean isOwner(Toz randomToz, Long randomUserId) {
		return randomToz.getQuestionerId() == randomUserId;
	}

	private void createDummyTozWithTags(User user) {
		Toz toz = new Toz("some title", user.getId(), dummyText.substring(0, 100 + random.nextInt(700)));
		tozMapper.save(toz);
		
		Collections.shuffle(dummyTags);
		for (int i = 0; i < random.nextInt(dummyTags.size()); i++)
			tozTagMapper.save(new Tag(toz.getId(), dummyTags.get(i)));
	}

	private User createDummyUserWithTags(int count) {
		User user = new User("user" + count + "@raptoz.com", "test", "user" + count, "");
		userMapper.save(user);
		
		Collections.shuffle(dummyTags);
		for (int j = 0; j < random.nextInt(dummyTags.size()); j++) {
			userTagMapper.save(new Tag(user.getId(), dummyTags.get(j)));
		}
		return user;
	}

	public void delete() {
		userMapper.deleteAll();
		userTagMapper.deleteAll();
		tozMapper.deleteAll();
		tozTagMapper.deleteAll();
		tozParticipantMapper.deleteAll();
	}
	
}
