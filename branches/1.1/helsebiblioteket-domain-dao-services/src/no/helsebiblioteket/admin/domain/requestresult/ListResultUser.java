package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.User;

@SuppressWarnings("serial")
public class ListResultUser implements Serializable {
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
