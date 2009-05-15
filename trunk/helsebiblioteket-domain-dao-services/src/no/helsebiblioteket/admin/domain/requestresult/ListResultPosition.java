package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Position;

public class ListResultPosition implements Serializable {
	private Position[] list;
	
	public ListResultPosition() {
	}
	public ListResultPosition(Position[] list){
		this.list = list;
	}
	
	public Position[] getList() {
		return list;
	}
	public void setList(Position[] list) {
		this.list = list;
	}
}
