package com.bank.model;
/**
 * Author:
 * Abdul Raheem
 */
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.entity.UserDetailsEntity;

public class UserPrinicipal implements UserDetails {
	private static final long serialVersionUID = 1L;

	private UserDetailsEntity user;

	public UserPrinicipal() {
		super();
	}

	public UserPrinicipal(UserDetailsEntity user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		//This is only for demo. Ignoring the implementation for now.
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//This is only for demo. Ignoring the implementation for now.
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//This is only for demo. Ignoring the implementation for now.
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}