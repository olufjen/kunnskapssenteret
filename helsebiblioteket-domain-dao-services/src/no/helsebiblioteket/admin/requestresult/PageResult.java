package no.helsebiblioteket.admin.requestresult;

import java.io.Serializable;

public class PageResult implements Serializable {
	private Integer number;
	private Integer skipped;
	private Integer total;
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer total) {
		this.number = total;
	}
	public Integer getSkipped() {
		return skipped;
	}
	public void setSkipped(Integer skipped) {
		this.skipped = skipped;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
