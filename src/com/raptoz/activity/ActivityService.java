package com.raptoz.activity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.raptoz.activity.Activity.Type;
import com.raptoz.user.User;

@Service
public class ActivityService {
	@Autowired ActivityRepository activityRepository;
	
	public void addActivity(Activity<? extends FootPrintable> activity) {
		activityRepository.save(activity);
	}

	public List<Activity<? extends FootPrintable>> getByUser(User user) {
		PageRequest pageable = new PageRequest(0, 3, Direction.DESC, "created");
		List<Activity<? extends FootPrintable>> activities = activityRepository.findByOwnerId(user.getId(), pageable);
		return activities;
	}
	
	public List<Activity<? extends FootPrintable>> getByType(ObjectId id, Type type) {
		return activityRepository.findByOwnerIdAndType(id, type);
	}
}