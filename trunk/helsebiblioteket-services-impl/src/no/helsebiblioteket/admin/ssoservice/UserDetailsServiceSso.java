package no.helsebiblioteket.admin.ssoservice;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;


@SuppressWarnings("serial")
public class UserDetailsServiceSso extends SsoService implements UserDetailsService {
	protected static final Log logger = LogFactory.getLog(UserDetailsServiceSso.class);

	@Override
	public Log getLogger() { return logger; }
	
	private UserDetailsService userDetailsService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		return userDetailsService.loadUserByUsername(username);
	}
	
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}