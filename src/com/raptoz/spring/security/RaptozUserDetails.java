package com.raptoz.spring.security;

import java.util.*;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.raptoz.security.Role;
import com.raptoz.user.User;
import com.raptoz.user.User.Status;

@Data
@SuppressWarnings("serial")
public class RaptozUserDetails implements UserDetails {
	private User user;
	private Collection<? extends GrantedAuthority> authorities;
	
	public RaptozUserDetails(User user) {
		this.user = user;
		this.authorities = getGrantedAuthorities(getRoles(user.getRoles()));
	}

	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(List<Role> roles) {
		List<String> roleStrings = new ArrayList<String>();
		
		for (Role role : roles) {
			roleStrings.add(role.getName());
		}
		
		return roleStrings;
	}

	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getNickname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getStatus() != Status.DEACTIVATED;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getStatus() == Status.ACTIVE;
	}

}
