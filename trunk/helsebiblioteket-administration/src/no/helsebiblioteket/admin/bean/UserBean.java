package no.helsebiblioteket.admin.bean;

import java.util.List;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.AdminService;

public class UserBean {
	private AdminService adminService;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public List<User> getUserList() {
		return adminService.getUserList();
	}
}