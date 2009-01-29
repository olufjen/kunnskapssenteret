package no.helsebiblioteket.admin.requestresult;

public class MorePageRequest<T> extends FirstPageRequest<T>{
	public int skip;
	public MorePageRequest(int skip, int maxResult) {
		super(maxResult);
		this.skip = skip;
	}
}
