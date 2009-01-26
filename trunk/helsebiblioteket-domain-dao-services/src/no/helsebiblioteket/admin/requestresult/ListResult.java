package no.helsebiblioteket.admin.requestresult;

public class ListResult<T> {
	private T[] list;
	public ListResult(T[] list){
		this.list = list;
	}
	public T[] getList() {
		return list;
	}
}
