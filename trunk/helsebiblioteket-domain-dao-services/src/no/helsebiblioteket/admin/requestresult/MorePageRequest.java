package no.helsebiblioteket.admin.requestresult;

public class MorePageRequest<T> extends FirstPageRequest<T>{
	public int last;
	public MorePageRequest(int last, int maxResult) {
		super(maxResult);
		this.last = last;
	}
}
