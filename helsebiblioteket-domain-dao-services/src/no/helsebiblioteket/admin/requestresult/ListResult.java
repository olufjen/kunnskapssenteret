package no.helsebiblioteket.admin.requestresult;

public class ListResult<T> {
	private T[] list;
	
	public ListResult() {
	}
	public ListResult(T[] list){
		this.list = list;
	}
	
	public T[] getList() {
		return list;
	}
	public void setList(T[] list) {
		this.list = list;
	}
}
