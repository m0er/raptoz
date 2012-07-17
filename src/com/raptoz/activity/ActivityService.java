package com.raptoz.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
	@Autowired ActivityRepository activityRepository;
	
	public void addActivity(Activity<? extends FootPrintable> activity) {
		activityRepository.save(activity);
	}
	
}
