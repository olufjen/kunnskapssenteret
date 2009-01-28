package no.helsebiblioteket.admin.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetailsService;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.UserService;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {
	private UserService userService;
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		SingleResult<User> lookup = this.userService.findUserByUsername(username);
		if(lookup instanceof EmptyResult){
			throw new UsernameNotFoundException("User not found: ");
		} else {
			ValueResult<User> value = (ValueResult<User>)lookup;
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
		GrantedAuthority[] result = new GrantedAuthority[user.getRoleList().size()];
		int i = 0;
		for (UserRole role : user.getRoleList()) {
			result[i++] = new GrantedAuthorityImpl(role.getName());
		}
		return result;
		// TODO: Use ROLE_ALLACCESS?
//		GrantedAuthority[] result = new GrantedAuthority[2];
//		result[0]= new GrantedAuthorityImpl("ROLE_ALLACCESS");
//		result[1]= new GrantedAuthorityImpl("ROLE_URLACCESS");
//		return result;
	}
}
