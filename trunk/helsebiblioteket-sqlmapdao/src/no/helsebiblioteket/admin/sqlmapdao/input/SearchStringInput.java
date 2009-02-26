package no.helsebiblioteket.admin.sqlmapdao.input;

public class SearchStringInput {
	private String value;
	private Integer fromId;
	private Integer toId;
	public SearchStringInput(String value, Integer fromId, Integer toId) {
		this.value = value;
		this.fromId = fromId;
		this.toId = toId;
	}
	public SearchStringInput() { }
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
}
