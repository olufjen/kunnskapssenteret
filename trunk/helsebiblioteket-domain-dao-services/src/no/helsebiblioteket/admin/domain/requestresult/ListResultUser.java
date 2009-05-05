package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.User;

public class ListResultUser {
	private User[] list;
	
	public ListResultUser() {
	}
	public ListResultUser(User[] list){
		this.list = list;
	}
	
	public User[] getList() {
		return list;
	}
	public void setList(User[] list) {
		this.list = list;
	}
}
