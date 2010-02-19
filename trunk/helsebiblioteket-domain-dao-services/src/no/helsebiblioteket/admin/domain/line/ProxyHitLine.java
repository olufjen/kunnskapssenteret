package no.helsebiblioteket.admin.domain.line;

import java.util.Date;

public class ProxyHitLine {
	private Integer memberOrgUnitId;
	private Integer supplierOrgUnitId;
	private String year;
	private String month;
	private String dayOfMonth;
	private String dayOfWeek;
	private String hour;
	private Integer count;
	private Boolean multipleMembers;
	private Date lastChanged;
	
	// GET/SET
	public Integer getMemberOrgUnitId() {
		return memberOrgUnitId;
	}
	public void setMemberOrgUnitId(Integer memberOrgUnitId) {
		this.memberOrgUnitId = memberOrgUnitId;
	}
	public Integer getSupplierOrgUnitId() {
		return supplierOrgUnitId;
	}
	public void setSupplierOrgUnitId(Integer supplierOrgUnitId) {
		this.supplierOrgUnitId = supplierOrgUnitId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Boolean getMultipleMembers() {
		return multipleMembers;
	}
	public void setMultipleMembers(Boolean multipleMembers) {
		this.multipleMembers = multipleMembers;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
