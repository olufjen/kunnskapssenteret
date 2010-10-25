package no.helsebiblioteket.admin.domain.parameter;

public class ProxyExportParameter {
	private Integer memberId;
	private Integer supplierId;
	private boolean byMember;
	private String period;
	private String from;
	private String to;
	private boolean hideUnknown;
	private boolean groupAll;
	private boolean groupType;

	public ProxyExportParameter(Integer memberId, Integer supplierId,
			boolean byMember, String period, String from, String to,
			boolean hideUnknown, boolean groupAll, boolean groupType) {
		super();
		this.memberId = memberId;
		this.supplierId = supplierId;
		this.byMember = byMember;
		this.period = period;
		this.from = from;
		this.to = to;
		this.hideUnknown = hideUnknown;
		this.groupAll = groupAll;
		this.groupType = groupType;
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
	
	public boolean isGroupAll() {
		return groupAll;
	}

	public void setGroupAll(boolean groupAll) {
		this.groupAll = groupAll;
	}

	public boolean isGroupType() {
		return groupType;
	}

	public void setGroupType(boolean groupType) {
		this.groupType = groupType;
	}
}