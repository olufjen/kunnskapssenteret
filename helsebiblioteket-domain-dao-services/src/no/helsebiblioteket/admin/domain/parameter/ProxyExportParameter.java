package no.helsebiblioteket.admin.domain.parameter;

public class ProxyExportParameter {
	private Integer memberId;
	private Integer supplierId;
	private boolean byMember;
	private String period;
	private String from;
	private String to;
	private boolean hideUnknown;

	public ProxyExportParameter(Integer memberId, Integer supplierId,
			boolean byMember, String period, String from, String to, boolean hideUnknown) {
		super();
		this.memberId = memberId;
		this.supplierId = supplierId;
		this.byMember = byMember;
		this.period = period;
		this.from = from;
		this.to = to;
		this.hideUnknown = hideUnknown;
	}

	public ProxyExportParameter() {
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
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

	public boolean getByMember() {
		return byMember;
	}

	public void setByMember(boolean byMember) {
		this.byMember = byMember;
	}
	public boolean isHideUnknown() {
		return hideUnknown;
	}
	public void setHideUnknown(boolean hideUnknown) {
		this.hideUnknown = hideUnknown;
	}
}
