package no.helsebiblioteket.admin.requestresult;

import no.helsebiblioteket.admin.domain.Position;

public class ListResultPosition {
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
