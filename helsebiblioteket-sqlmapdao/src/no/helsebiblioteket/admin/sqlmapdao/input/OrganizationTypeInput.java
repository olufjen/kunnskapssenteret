package no.helsebiblioteket.admin.sqlmapdao.input;

import java.util.List;

import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;

public class OrganizationTypeInput {
	private List<OrganizationTypeKey> types;
	private Integer fromId;
	private Integer toId;
	public OrganizationTypeInput(List<OrganizationTypeKey> types,
			Integer fromId, Integer toId) {
		this.types = types;
		this.fromId = fromId;
		this.toId = toId;
	}
	
	public OrganizationTypeInput() {
	}
	
	public List<OrganizationTypeKey> getTypes() {
		return types;
	}

	public void setTypes(List<OrganizationTypeKey> types) {
		this.types = types;
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
