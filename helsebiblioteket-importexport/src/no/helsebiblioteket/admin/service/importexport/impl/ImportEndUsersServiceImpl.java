package no.helsebiblioteket.admin.service.importexport.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.service.importexport.ImportEndUsersService;
import no.helsebiblioteket.admin.service.importexport.ldap.LDAPLookupUtil;
import no.helsebiblioteket.admin.service.importexport.ldap.domain.LDAPUser;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultRole;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;

/**
 * This class:
 * - fetches existing users from existing LDAP-server, 
 * - stores the fetched users in new structure in a new database
 * Users affected: All existing endusers: HPR, Stud, Emp - users.
 * @author ltg
 *
 */

public class ImportEndUsersServiceImpl implements ImportEndUsersService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8960490923903576333L;
	LDAPLookupUtil ldapLookupUtil;
	UserService userService;
	
	static Role[] roleOtherArray;
	static Role[] roleHealthPersonellArray;
	static Role[] roleStudentArray;
	static Position position = new Position();
	

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
	
	@SuppressWarnings("unchecked")
	public void importAllEndUsers() {
		try {
			SingleResultSystem systemResult = userService.getSystemByKey(SystemKey.helsebiblioteket_admin);
			if (systemResult instanceof EmptyResultSystem) {
				throw new Exception("non existing system for system key '" + SystemKey.helsebiblioteket_admin + "");
			}
			System system = ((ValueResultSystem)systemResult).getValue();
			
			SingleResultRole roleOtherResult = userService.getRoleByKeySystem(UserRoleKey.health_personnel_other, system);
			if (roleOtherResult instanceof EmptyResultRole) {
				throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.health_personnel_other + "'");
			}
			Role roleOther = (Role) ((ValueResultRole) roleOtherResult).getValue();
			
			SingleResultRole roleHealthPersonnelResult = userService.getRoleByKeySystem(UserRoleKey.health_personnel, system);
			if (roleHealthPersonnelResult instanceof EmptyResultRole) {
				throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.health_personnel + "'");
			}
			Role roleHealthPersonell = (Role) ((ValueResultRole) roleHealthPersonnelResult).getValue();
			
			SingleResultRole roleStudentResult = userService.getRoleByKeySystem(UserRoleKey.student, system);
			if (roleStudentResult instanceof EmptyResultRole) {
				throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.student + "'");
			}
			Role roleStudent = (Role) ((ValueResultRole) roleStudentResult).getValue();
			
			roleOtherArray = new Role[] { roleOther };
			roleHealthPersonellArray = new Role[] { roleHealthPersonell };
			roleStudentArray = new Role[] { roleStudent };
			
			Collection<LDAPUser> allEndUsersAsLdapUsers = new ArrayList<LDAPUser>();
			try {
				allEndUsersAsLdapUsers = ldapLookupUtil.getAllLDAPUsers();
			} catch (NamingException e) {
				// TODO Fase2: Log this?
				// leif sier: ja
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Fase2: Log this?
				// leif sier: ja
				e.printStackTrace();
			}
			
			User helsebibliotekUser = null;
			Map<String, User> allEndUsersAsHelsebibliotekUsers = new HashMap<String, User>();
			
			for (LDAPUser ldapUser : allEndUsersAsLdapUsers) {
				helsebibliotekUser = new User();
				helsebibliotekUser = populateUser(ldapUser);
				if (null != helsebibliotekUser) {
					if (null == helsebibliotekUser.getUsername() || "".equals(helsebibliotekUser.getUsername())) {
						java.lang.System.out.println("En bruker uten brukernavn ble funnet, brukeren ble ikke lagt til. Brukerens toString er '" + helsebibliotekUser.toString() + "'");
					} else if (allEndUsersAsHelsebibliotekUsers.get(helsebibliotekUser.getUsername()) != null) {
						java.lang.System.out.println("Dupikat bruker ble funnet: '" + helsebibliotekUser.getUsername() + "'. Denne ble ikke lagt til, dette må gjøres manuelt"); 
					} else {
						allEndUsersAsHelsebibliotekUsers.put(helsebibliotekUser.getUsername(), helsebibliotekUser);
					}
				}
			}
			
			for (String userName : allEndUsersAsHelsebibliotekUsers.keySet()) {
				userService.insertUser(allEndUsersAsHelsebibliotekUsers.get(userName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private User populateUser(LDAPUser ldapUser) {
        User user = new User();
        Person person = new Person();
        
        person.setPosition(position);
        
        if (null == this.commonPassword) {
        	user.setPassword(ldapUser.getUserPassword());
        } else {
        	user.setPassword(this.commonPassword);
        }
        
        user.setUsername(ldapUser.getUid());
        
        String studentOrHprOrDateOfBirth = null;
        
        studentOrHprOrDateOfBirth = ldapUser.getEmployeeNumber(); 
        
        if ("Emp".equalsIgnoreCase(ldapUser.getEmployeeType())) {
        	user.setRoleList(roleOtherArray);
        	person.setDateOfBirth(studentOrHprOrDateOfBirth);
        }
        if ("Stud".equalsIgnoreCase(ldapUser.getEmployeeType())) {
        	user.setRoleList(roleStudentArray);
        	person.setStudentNumber(studentOrHprOrDateOfBirth);
        }
        if ("HPR".equalsIgnoreCase(ldapUser.getEmployeeType())) {
        	user.setRoleList(roleHealthPersonellArray);
        	person.setHprNumber(studentOrHprOrDateOfBirth);
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
        return user;
    }

}
