package no.helsebiblioteket.admin.requestresult;

public class PageRequest {
	public Integer skip;
	public Integer maxResult;
	public PageRequest() {
		super();
	}
	public PageRequest(Integer skip, Integer maxResult) {
		this.skip = skip;
		this.maxResult = maxResult;
	}
	public Integer getSkip() {
		return skip;
	}
	public void setSkip(Integer skip) {
		this.skip = skip;
	}
	public Integer getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}
}
