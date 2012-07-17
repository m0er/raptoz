package com.raptoz.activity;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.raptoz.activity.Activity.Type;
import com.raptoz.post.Post;
import com.raptoz.post.PostRepository;
import com.raptoz.reply.Reply;
import com.raptoz.reply.ReplyRepository;
import com.raptoz.user.User;
import com.raptoz.user.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class ActivityRepositoryTest {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired ActivityRepository activityRepository;
	@Autowired UserRepository userRepository;
	@Autowired PostRepository postRepository;
	@Autowired ReplyRepository replyRepository;
	
	@Autowired MongoTemplate mongoTemplate;
	
	User user;
	Post post;
	Reply reply;

	Post[] posts;
	Reply[] replies;
	
	Stack<Date> timeStack = new Stack<>();
	
	@Before
	public void setUp() throws Exception {
		initDummyUser();
		initSingleDummyContents();
		initArrayDummyContents();
	}

	private void initDummyUser() {
		user = new User("luckychips@raptoz.com", "test", "luckychips", "");
		user.setJoined(new Date());
		
		userRepository.save(user);
		user = userRepository.findOneSimpleById(user.getId());
	}

	private void initArrayDummyContents() {
		posts = new Post[2];
		replies = new Reply[2];
		
		initTimeStack();
		addDummyPosts(posts);
		addDummyReplies(replies);
		makeDummyActivities(posts);
		makeDummyActivities(replies);
	}

	private void initSingleDummyContents() {
		post = new Post("title", "content", user);
		reply = new Reply("reply", user);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void crud() throws Exception {
		Activity<Post> activity = new Activity<>(post);
		
		activityRepository.save(activity);
		
		Activity<FootPrintable> foundActivity = (Activity<FootPrintable>) activityRepository.findOne(activity.getId());
		assertThat(activity.getId(), is(foundActivity.getId()));
		
		reply = new Reply("reply", user);
		foundActivity.setContent(reply);
		
		activityRepository.save(foundActivity);
		
		Activity<FootPrintable> newActivity = (Activity<FootPrintable>) activityRepository.findOne(foundActivity.getId());
		assertThat(newActivity.getType(), is(Type.REPLY));
		
		activityRepository.delete(foundActivity.getId());
	}
	
	@Test
	public void Content_타입_유지() throws Exception {
		Activity<Post> activity = new Activity<>(post);
		activityRepository.save(activity);
		
		Activity<? extends FootPrintable> foundActivity = activityRepository.findOne(activity.getId());
		logger.info(foundActivity.toString());
		
		FootPrintable content = foundActivity.getContent();
		assertThat(content instanceof Post, is(true));
		
		logger.info(content.getContentString());
	}
	
	@Test
	public void 최신순으로_활동내역_가져오기() throws Exception {
		Page<Activity<? extends FootPrintable>> activityPage = activityRepository.findAll(new PageRequest(0, 4, Direction.DESC, "created"));
		assertThat(activityPage.getNumberOfElements(), is(4));

		List<Activity<? extends FootPrintable>> activities = activityPage.getContent();
		assertThat(activities.get(0).getType(), is(Type.REPLY));
		assertThat(activities.get(1).getType(), is(Type.POST));
		assertThat(activities.get(2).getType(), is(Type.REPLY));
		assertThat(activities.get(3).getType(), is(Type.POST));
		
//		Query query = new Query();
//		query.sort().on("created", Order.DESCENDING);
//		query.fields().include("id").include("type").include("created");
//		query.limit(4);
//		
//		List<Activity> list = mongoTemplate.find(query, Activity.class);
//		logger.info(list.toString());
	}
	
	private void initTimeStack() {
		Long current = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(current - 1000000L);
		timeStack.add(calendar.getTime());
		
		calendar.setTimeInMillis(current + 200000L);
		timeStack.add(calendar.getTime());
		
		calendar.setTimeInMillis(current - 5000000L);
		timeStack.add(calendar.getTime());
		
		calendar.setTimeInMillis(current);
		timeStack.add(calendar.getTime());
	}

	private void addDummyPosts(Post...posts) {
		for (int i = 0; i < posts.length; i++) {
			posts[i] = new Post("title" + i, "content" + i, user);
			posts[i].setCreated(timeStack.pop());
			posts[i].setViewCount(0L);
		}
		
		postRepository.save(Arrays.asList(posts));
	}
	
	private void addDummyReplies(Reply...replies) {
		for (int i = 0; i < replies.length; i++) {
			replies[i] = new Reply("reply" + i, user);
			replies[i].setCreated(timeStack.pop());
		}
		
		replyRepository.save(Arrays.asList(replies));
	}
	
	private void makeDummyActivities(FootPrintable...footPrintables) {
		for (FootPrintable footPrintable : footPrintables) {
			Activity<FootPrintable> dummyActivity = new Activity<>(footPrintable);
			activityRepository.save(dummyActivity);
		}
	}

	@After
	public void tearDown() throws Exception {
		postRepository.deleteAll();
		userRepository.deleteAll();
		activityRepository.deleteAll();
		replyRepository.deleteAll();
	}

}
