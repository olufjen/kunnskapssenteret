package no.helsebiblioteket.admin.service.importexport.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sun.security.auth.module.LdapLoginModule;

import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.service.impl.UserServiceImpl;
import no.helsebiblioteket.admin.service.importexport.ldap.LDAPLookupUtil;
import no.helsebiblioteket.admin.service.importexport.ldap.domain.LDAPUser;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.User;

/**
 * This class:
 * - fetches existing users from existing LDAP-server, 
 * - stores the fetched users in new structure in a new database
 * Users affected: All existing endusers: HPR, Stud, Emp - users.
 * @author ltg
 *
 */

public class LocateFaultyUsersMain {
	LDAPLookupUtil ldapLookupUtil;
	UserService userService;

	String commonPassword;
	private static final Logger LOG = Logger.getLogger(LocateFaultyUsersMain.class.toString());
	
	public static void main(String args[]) {
		LocateFaultyUsersMain locateFaultyUsers = new LocateFaultyUsersMain();
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		//userServiceImpl.setUserDao(new SqlMapUserDao());
		locateFaultyUsers.setUserService(new UserServiceImpl());
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setLdapLookupUtil(LDAPLookupUtil util) {
		this.ldapLookupUtil = util;
	}
	
	public void setCommonPassword(String commonPassword) {
		this.commonPassword = commonPassword;
	}
	
	public void importAllEndUsers() {
		Collection<LDAPUser> allEndUsersAsLdapUsers = new ArrayList();
		try {
			allEndUsersAsLdapUsers = ldapLookupUtil.getAllLDAPUsers();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User helsebibliotekUser = null;
		Collection<User> allEndUsersAsHelsebibliotekUsers = new ArrayList();
		for (LDAPUser ldapUser : allEndUsersAsLdapUsers) {
			helsebibliotekUser = new User();
			helsebibliotekUser = populateUser(ldapUser);
			allEndUsersAsHelsebibliotekUsers.add(helsebibliotekUser);
		}
		
		for (User user : allEndUsersAsHelsebibliotekUsers) {
			// FIXME: Are they unique? Need test?
//			userService.createUser(user);
			userService.insertUser(user);
		}
	}
	
	private User populateUser(LDAPUser ldapUser) {
        User user = new User();
        if (null == this.commonPassword) {
        	user.setPassword(ldapUser.getUserPassword());
        } else {
        	user.setPassword(this.commonPassword);
        }
        user.setUsername(ldapUser.getUid());
        Person person = new Person();
        person.setFirstName(ldapUser.getGivenName());
        person.setLastName(ldapUser.getSureName());
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail(ldapUser.getEmail());
        Profile userProfile = new Profile();
        try {
        	userProfile.setParticipateSurvey((ldapUser.getMobile() != null) ? Boolean.valueOf(ldapUser.getMobile()) : false);
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "'survey' has wrong format, should have been boolean: " + ldapUser.getMobile());
        }
        try {
        	userProfile.setReceiveNewsletter((ldapUser.getPager() != null) ? Boolean.valueOf(ldapUser.getPager()) : false);
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "'newsletter' has wrong format, should have been boolean: " + ldapUser.getPager());
        }
        person.setProfile(userProfile);
        person.setContactInformation(contactInformation);
        user.setPerson(person);
        //TODO: Use member organization here?
        user.setOrganization(new MemberOrganization().getOrganization());
        //TODO
        /*user.setDn(ldapUser.getDn());
        user.setUid(ldapUser.getUid());
        user.setFirstName(ldapUser.getGivenName());
        user.setSurName(ldapUser.getSureName());
        user.setEmail(ldapUser.getEmail());
        user.setOrg(ldapUser.getO());
        user.setPassword(ldapUser.getUserPassword());
        user.setEmployeeType(ldapUser.getEmployeeType());
        user.setEmployeeNumber(ldapUser.getEmployeeNumber());
        user.setNewsletter(null != ldapUser.getMobile() ? Boolean.valueOf(ldapUser.getMobile()) : null);
        user.setSurvey(null != ldapUser.getPager() ? Boolean.valueOf(ldapUser.getPager()) : null); */
        return user;
    }
}