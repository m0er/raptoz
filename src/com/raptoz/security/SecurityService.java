package com.raptoz.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.raptoz.spring.security.RaptozUserDetails;
import com.raptoz.user.User;

@Service
public class SecurityService {
	
	public User getCurrentUser() {
		Authentication authentication = getAuthentication();
		
		if (authentication == null) return null;
		
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return ((RaptozUserDetails) principal).getUser();
		} else {
			return null;
		}
	}
	
	public boolean isAuthenticated() {
		return getAuthentication().getPrincipal() instanceof UserDetails;
	}
	
	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
