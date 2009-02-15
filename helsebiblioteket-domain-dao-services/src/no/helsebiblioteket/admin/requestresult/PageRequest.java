package no.helsebiblioteket.admin.requestresult;

public abstract class PageRequest {
	public int maxResult;
	public PageRequest(int maxResult) {
		this.maxResult = maxResult;
	}
}
