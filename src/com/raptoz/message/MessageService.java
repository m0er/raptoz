package com.raptoz.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired MessageRepository messageRepository;
	
	public void send(Message message) {
		messageRepository.save(message);
	}
}
