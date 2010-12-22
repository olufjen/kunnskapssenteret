package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Role;

@SuppressWarnings("serial")
public class ListResultRole implements Serializable {
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
