package com.raptoz.participant;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.raptoz.toz.*;
import com.raptoz.user.*;

@Service("tozParticipantService")
public class TozParticipantService {
	@Autowired TozParticipantMapper tozParticipantMapper;
	@Autowired UserMapper userMapper;
	
	public void add(TozParticipant tozParticipant) {
		tozParticipantMapper.save(tozParticipant);
	}

	public void delete(Long id) {
		tozParticipantMapper.deleteById(id);
	}

	public List<TozParticipant> get(Long tozId) {
		List<TozParticipant> tozParticipantList = tozParticipantMapper.findAllByTozId(tozId);
		for (TozParticipant tozParticipant : tozParticipantList)
			tozParticipant.setParticipant(userMapper.findOne(tozParticipant.getUserId()));
		return tozParticipantList;
	}
}
