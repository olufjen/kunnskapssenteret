package no.helsebiblioteket.admin.domain.parameter;

import java.util.ArrayList;
import java.util.List;

public class ProxyHitParameterList {
	private List<ProxyHitParameter> list = new ArrayList<ProxyHitParameter>();
	public List<ProxyHitParameter> getList() {
		return list;
	}
	public void setList(List<ProxyHitParameter> list) {
		this.list = list;
	}
}
