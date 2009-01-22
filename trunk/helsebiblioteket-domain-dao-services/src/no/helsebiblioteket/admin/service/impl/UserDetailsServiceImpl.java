package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetailsService;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {
	private UserDao userDao  ;
	
	public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
	}
		
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user=  userDao.findUser(username);
		// TODO: Should this not allready have been fetched?
		// Must set it here or makeAcegiUser will crash!
		if(user.getRoleList() == null){
			user.setRoleList(new ArrayList<Role>());
		}
		if(user !=null){
//			user.setPassword("qaa");
			return makeAcegiUser(user);
		}
		else throw new UsernameNotFoundException("User not found: " );
	}

	private org.springframework.security.userdetails.User makeAcegiUser(User user) {
		return new org.springframework.security.userdetails.User(user.getUsername(),
				user.getPassword(), true, true, true, true,
				makeGrantedAuthorities(user));
	}

	private GrantedAuthority[] makeGrantedAuthorities(User user) {
	
		GrantedAuthority[] result = new GrantedAuthority[user.getRoleList().size()];
		int i = 0;
		for (Role role : user.getRoleList()) {
			result[i++] = new GrantedAuthorityImpl(role.getName());
		}
		return result; 
//		GrantedAuthority[] result = new GrantedAuthority[2];
//		result[0]= new GrantedAuthorityImpl("ROLE_ALLACCESS");
//		result[1]= new GrantedAuthorityImpl("ROLE_URLACCESS");
//		return result;
	}

}
