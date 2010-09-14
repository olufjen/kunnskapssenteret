package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

@SuppressWarnings("serial")
public class UserDetailsServiceWeb extends BasicWebService implements UserDetailsService {
	protected static final Log logger = LogFactory.getLog(UserDetailsServiceWeb.class);
	private QName loadUserByUsernameName;
	@Override
	public Log getLogger() { return logger; }
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Object[] args = new Object[] { username };
		Class[] returnTypes = new Class[] { UserDetails.class };
		return (UserDetails)invoke(this.loadUserByUsernameName, args, returnTypes);
	}
	public void setLoadUserByUsernameName(QName loadUserByUsernameName) {
		this.loadUserByUsernameName = loadUserByUsernameName;
	}
}
