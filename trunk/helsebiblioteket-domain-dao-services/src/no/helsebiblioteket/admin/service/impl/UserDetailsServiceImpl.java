package no.helsebiblioteket.admin.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetailsService;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultUser;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.service.UserService;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {
	private UserService userService;
	public UserDetailsServiceImpl() { }
	public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
	}
	/**
	 * Loads the user object using UserService. Is the implementation
	 * required by org.springframework.security.userdetails.UserDetailsService
	 * 
	 * Creates a org.springframework.security.userdetails.UserDetails object.
	 * 
	 * The other methods are helpers for this.
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		SingleResultUser lookup = this.userService.findUserByUsername(username);
		if(lookup instanceof EmptyResultUser){
			throw new UsernameNotFoundException("User not found: ");
		} else {
			ValueResultUser value = (ValueResultUser)lookup;
			User user = value.getValue();
			return makeAcegiUser(user);
		}
	}
	private org.springframework.security.userdetails.User makeAcegiUser(User user) {
		return new org.springframework.security.userdetails.User(user.getUsername(),
				user.getPassword(), true, true, true, true,
				makeGrantedAuthorities(user));
	}
	private GrantedAuthority[] makeGrantedAuthorities(User user) {
		GrantedAuthority[] result = new GrantedAuthority[user.getRoleList().length];
		int i = 0;
		for (Role role : user.getRoleList()) 
			result[i++] = new GrantedAuthorityImpl("ROLE_"+role.getKey().toString());
		return result;
		// TODO: Use ROLE_ALLACCESS?
//		GrantedAuthority[] result = new GrantedAuthority[2];
//		result[0]= new GrantedAuthorityImpl("ROLE_ALLACCESS");
//		result[1]= new GrantedAuthorityImpl("ROLE_URLACCESS");
//		return result;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
