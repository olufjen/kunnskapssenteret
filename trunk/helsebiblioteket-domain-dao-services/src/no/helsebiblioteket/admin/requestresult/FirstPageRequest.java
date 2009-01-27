package no.helsebiblioteket.admin.requestresult;

public class FirstPageRequest<T> extends PageRequest<T>{
	public FirstPageRequest(int maxResult) {
		super(maxResult);
	}
}
