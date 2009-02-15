package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.Role;

public class ListResultRole {
	private Role[] list;
	
	public ListResultRole() {
	}
	public ListResultRole(Role[] list){
		this.list = list;
	}
	
	public Role[] getList() {
		return list;
	}
	public void setList(Role[] list) {
		this.list = list;
	}
}
