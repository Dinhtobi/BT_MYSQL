package com.assignment.java.Security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.assignment.java.Entities.User;

public class UserPrincipal implements UserDetails {

	private String email;
	
	private  Collection<? extends GrantedAuthority> authorities;
	
	private String password ;
	
	private int id;
	
	private Boolean enabled;
	
	public UserPrincipal() {
	}


	public UserPrincipal(int id, String email, String password, Boolean enabled, Collection<? extends GrantedAuthority> authorities ) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
	}


	public UserPrincipal create(User user) {
		List<GrantedAuthority> roles = user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString())).collect(Collectors.toList());
		return new UserPrincipal(user.getId(), user.getEmail() ,user.getPassword(),user.getEnabled(), roles);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled ;
	}

}
