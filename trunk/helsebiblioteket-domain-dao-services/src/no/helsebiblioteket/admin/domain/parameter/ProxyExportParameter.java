package no.helsebiblioteket.admin.domain.parameter;

public class ProxyExportParameter {
	private String memberId;
	private String supplierId;
	private String period;
	private String from;
	private String to;

	public ProxyExportParameter(String memberId, String supplierId,
			String period, String from, String to) {
		super();
		this.memberId = memberId;
		this.supplierId = supplierId;
		this.period = period;
		this.from = from;
		this.to = to;
	}

	public ProxyExportParameter() {
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
