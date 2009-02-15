package no.helsebiblioteket.admin.requestresult;

public class MorePageRequest extends PageRequest{
	public int skip;
	public MorePageRequest(int skip, int maxResult) {
		super(maxResult);
		this.skip = skip;
	}
}
