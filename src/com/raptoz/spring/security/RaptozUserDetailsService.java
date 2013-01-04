package com.raptoz.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

import com.raptoz.user.UserRepository;

public class RaptozUserDetailsService implements UserDetailsService {
	@Autowired private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		com.raptoz.user.User domainUser = userRepository.findOneByEmail(username);
		
		if (domainUser == null) {
			throw new UsernameNotFoundException(username + " 사용자를 찾을 수 없습니다.");
		}
		
		return new RaptozUserDetails(domainUser);
	}
}
