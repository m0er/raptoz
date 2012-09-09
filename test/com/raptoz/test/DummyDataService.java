package com.raptoz.test;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.raptoz.post.*;
import com.raptoz.reply.*;
import com.raptoz.tag.*;
import com.raptoz.user.*;

@Service("dummyDataService")
public class DummyDataService {
	@Autowired TagRepository tagRepository;
	@Autowired UserRepository userRepository;
	@Autowired PostRepository postRepository;
	@Autowired ReplyRepository replyRepository;
	
	private static final int DUMMY_DATA_COUNT = 100;
	private final Calendar calendar = Calendar.getInstance();
	
	Random random = new Random(System.currentTimeMillis());
	String dummyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur mattis adipiscing nunc sit amet consectetur. Donec vitae molestie neque. Aliquam fringilla velit ut massa accumsan sed lacinia metus aliquet. Sed porttitor pellentesque mi, sed placerat arcu vehicula vitae. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec ornare congue ligula ut placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc interdum, mi vel varius faucibus, nibh justo fermentum nisi, vitae tincidunt neque velit sit amet nunc. Donec vulputate augue nec leo dignissim a fermentum purus auctor. Nam feugiat ultricies orci, tempor condimentum libero viverra et. Mauris massa enim, sollicitudin id varius at, egestas eu erat.";
	List<Tag> dummyTags = new TagBuilder().add("coffee", "waffle", "java", "apple", "macbook", "web").build(); 
	String[] dummyWords = dummyText.split(" ");
	
	public void create() {
		// create admin account
		User dummyAdmin = new User("admin@raptoz.com", "test", "admin", "");
		dummyAdmin.setJoined(new Date());
		userRepository.save(dummyAdmin);
		
		// save dummy tags
		tagRepository.save(dummyTags);
		
		// save dummy users
		for (int count = 0; count < DUMMY_DATA_COUNT; count++) {
			User user = new User("user" + count + "@raptoz.com", "test", "user" + count, "");
			calendar.setTimeInMillis(System.currentTimeMillis() + random.nextInt(10000));
			user.setJoined(calendar.getTime());
			
			Collections.shuffle(dummyTags);
			
			List<Tag> dummyTagSublist = dummyTags.subList(0, random.nextInt(dummyTags.size()));
			user.setTags(dummyTagSublist);
			
			userRepository.save(user);
			
			User simpleUser = userRepository.findOneSimpleById(user.getId());
			
			// write dummy posts
			Post dummyPost = new Post("title" + count, dummyText.substring(0, 100 + random.nextInt(700)), simpleUser);
			calendar.setTimeInMillis(System.currentTimeMillis() + random.nextInt(10000));
			dummyPost.setCreated(calendar.getTime());
			dummyPost.setTags(dummyTags.subList(0, random.nextInt(dummyTags.size())));
			
			postRepository.save(dummyPost);
		}
		
		// write dummy replies
		addRepliesToEverywhere();
	}

	private void addRepliesToEverywhere() {
		List<User> users = userRepository.findAllSimple();
		List<Post> posts = postRepository.findAll();
		
		for (int i = 0; i < 200; i++) {
			Post randomPost = posts.get(random.nextInt(posts.size()));
			User randomUser = users.get(random.nextInt(users.size()));
			
			Reply dummyReply = null;
			
			// write reply
			if (isOwner(randomPost, randomUser.getId()))
				dummyReply = new Reply("my post..." + i, randomUser);
			else
				dummyReply = new Reply(generateDummyComment() + i, randomUser);
			
			dummyReply.setPostId(randomPost.getId());
			calendar.setTimeInMillis(System.currentTimeMillis() + random.nextInt(10000));
			dummyReply.setCreated(calendar.getTime());
			
			replyRepository.save(dummyReply);
			
			// add reply to post
			List<ObjectId> dummyReplyIds = randomPost.getReplyIds();
			
			ObjectId dummyId = dummyReply.getId();
			
			if (dummyReplyIds == null) {
				dummyReplyIds = new ArrayList<ObjectId>();
				dummyReplyIds.add(dummyId);
			} else { 
				dummyReplyIds.add(dummyId);
			}
			
			randomPost.setReplyIds(dummyReplyIds);
			
			// update post
			postRepository.save(randomPost);
		}
	}
	
	private String generateDummyComment() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < random.nextInt(30); i++)
			builder.append(dummyWords[random.nextInt(dummyWords.length)]);
		return builder.toString();
	}

	private boolean isOwner(Post randomPost, ObjectId randomUserId) {
		return randomPost.getWriter().getId() == randomUserId;
	}

	public void delete() {
		tagRepository.deleteAll();
		userRepository.deleteAll();
		postRepository.deleteAll();
		replyRepository.deleteAll();
	}
	
}
