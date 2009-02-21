package no.helsebiblioteket.admin.requestresult;

public class PageRequestWithString {
	private Integer skip;
	private Integer maxResult;
	private String stringValue;
	public PageRequestWithString(Integer skip, Integer maxResult, String stringValue) {
		this.skip = skip;
		this.maxResult = maxResult;
		this.stringValue = stringValue;
	}
	public PageRequestWithString() { }
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
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
}
