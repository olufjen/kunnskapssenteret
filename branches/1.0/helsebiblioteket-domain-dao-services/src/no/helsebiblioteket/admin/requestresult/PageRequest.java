package no.helsebiblioteket.admin.requestresult;

public class PageRequest {
	private Integer skip;
	private Integer maxResult;
	public PageRequest() { }
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
