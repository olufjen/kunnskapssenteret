package no.helsebiblioteket.admin.service.importexport.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.service.importexport.ImportEndUsersService;
import no.helsebiblioteket.admin.service.importexport.ldap.LDAPLookupUtil;
import no.helsebiblioteket.admin.service.importexport.ldap.domain.LDAPUser;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

/**
 * This class:
 * - fetches existing users from existing LDAP-server, 
 * - stores the fetched users in new structure in a new database
 * Users affected: All existing endusers: HPR, Stud, Emp - users.
 * @author ltg
 *
 */

public class ImportEndUsersServiceImpl implements ImportEndUsersService {
	LDAPLookupUtil ldapLookupUtil;
	UserService userService;
	
	static List<Role> roleOtherList = new ArrayList<Role>();
	static List<Role> roleHealthPersonellList = new ArrayList<Role>();
	static List<Role> roleStudentList = new ArrayList<Role>();
	
	static {
		Role roleOther = new Role();
		roleOther.setRoleId(4);
		roleOtherList.add(roleOther);
		
		Role roleHealthPersonell = new Role();
		roleHealthPersonell.setRoleId(2);
		roleHealthPersonellList.add(roleHealthPersonell);
		
		Role roleStudent = new Role();
		roleStudent.setRoleId(3);
		roleStudentList.add(roleStudent);
	}

	String commonPassword;
	private static final Logger LOG = Logger.getLogger(ImportEndUsersServiceImpl.class.toString());

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
			if (null != helsebibliotekUser) {
				allEndUsersAsHelsebibliotekUsers.add(helsebibliotekUser);
			}
		}
		
		for (User user : allEndUsersAsHelsebibliotekUsers) {
			// FIXME: Are they unique or check for existing?
//			userService.createUser(user);
			userService.insertUser(user);
		}
	}
	
	private User populateUser(LDAPUser ldapUser) {
        User user = new User();
        Person person = new Person();
        
        if (null == this.commonPassword) {
        	user.setPassword(ldapUser.getUserPassword());
        } else {
        	user.setPassword(this.commonPassword);
        }
        
        user.setUsername(ldapUser.getUid());
        
        String studentOrHprNumber = null;
        
        studentOrHprNumber = ldapUser.getEmployeeNumber(); 
        
        if ("Emp".equalsIgnoreCase(ldapUser.getEmployeeType())) {
        	user.setRoleList(roleOtherList);
        }
        if ("Stud".equalsIgnoreCase(ldapUser.getEmployeeType())) {
        	user.setRoleList(roleStudentList);
        	person.setStudentNumber(studentOrHprNumber);
        }
        if ("HPR".equalsIgnoreCase(ldapUser.getEmployeeType())) {
        	user.setRoleList(roleHealthPersonellList);
        	person.setStudentNumber(studentOrHprNumber);
        }
        
        if (user.getRoleList() == null) {
        	LOG.log(Level.SEVERE, "No roles for user '" + ldapUser.getUid() + "', ommiting user");
        	return null;
        }
        
        person.setFirstName(ldapUser.getGivenName());
        person.setLastName(ldapUser.getSureName());
        
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail(ldapUser.getEmail());
        Profile userProfile = new Profile();
        try {
        	userProfile.setParticipateSurvey((ldapUser.getMobile() != null) ? Boolean.valueOf(ldapUser.getMobile()) : false);
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "'survey' for user '" + ldapUser.getUid() + "' has wrong format, should have been boolean: " + ldapUser.getMobile());
        }
        try {
        	userProfile.setReceiveNewsletter((ldapUser.getPager() != null) ? Boolean.valueOf(ldapUser.getPager()) : false);
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "'newsletter' for user '" + ldapUser.getUid() + "' has wrong format, should have been boolean: " + ldapUser.getPager());
        }
        person.setProfile(userProfile);
        person.setContactInformation(contactInformation);
        user.setPerson(person);
        //user.setOrganization(new Organization());
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
