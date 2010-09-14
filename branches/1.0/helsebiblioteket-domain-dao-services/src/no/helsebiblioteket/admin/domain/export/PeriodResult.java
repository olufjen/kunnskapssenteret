package no.helsebiblioteket.admin.domain.export;

public class PeriodResult {
	private String period;
	private Integer count;
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
