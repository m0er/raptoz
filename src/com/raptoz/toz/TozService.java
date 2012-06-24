package com.raptoz.toz;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.raptoz.tag.*;
import com.raptoz.participant.*;
import com.raptoz.user.*;

@Service("tozService")
public class TozService {
	@Autowired private TozMapper tozMapper;
	@Autowired private UserMapper userMapper;
	@Autowired private TozTagMapper tozTagMapper;
	@Autowired private TozParticipantMapper tozParticipantMapper;
	
	public void create(User loginUser, Toz toz) {
		toz.setQuestionerId(loginUser.getId());
		tozMapper.save(toz);
	}

	public Toz get(Long tozId) {
		Toz toz = tozMapper.findOne(tozId);
		toz.setQuestioner(questioner(toz));
		return toz;
	}
	
	public List<Toz> getByTagWithAllTags(String value) {
		List<Tag> foundTagList = tozTagMapper.findAllByValue(value);
		
		List<Toz> tozList = new ArrayList<Toz>();
		for (Tag foundTag : foundTagList) {
			Long ownerId = foundTag.getOwnerId();
			Toz toz = tozMapper.findOne(ownerId);
			List<Tag> tozTagList = tozTagMapper.findAllByOwnerId(ownerId);
			toz.setTagList(tozTagList);
			toz.setTozParticipantList(tozParticipantMapper.findAllByTozId(toz.getId()));
			tozList.add(toz);
		}
		
		return tozList;
	}

	private User questioner(Toz toz) {
		return userMapper.findOne(toz.getQuestionerId());
	}

}
