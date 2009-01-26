package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.UserRoleLine;

public interface AccessDao {
	
	public Access getAccessById(Integer accessId);
	
	public List<Access> getAccessListByUserId(Integer userId);
	
	public List<Access> getAccessListByUserRoleId(Integer userRoleId);
	
	public List<Access> getAccessListByOrganizationTypeId(Integer organizationTypeId);
	
	public List<Access> getAccessListByOrganizationId(Integer organizationId);
	
	public void insertAccess(Access access);
	
	public void deleteAccess(Access access);
	
	public void updateAccess(Access access);
}
