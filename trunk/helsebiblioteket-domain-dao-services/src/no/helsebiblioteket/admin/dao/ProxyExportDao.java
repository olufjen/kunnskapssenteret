package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.export.ProxyResult;
import no.helsebiblioteket.admin.domain.parameter.ProxyExportParameter;

public interface ProxyExportDao {
	// Fetch proxy result
	public List<ProxyResult> getProxyExportList(ProxyExportParameter parameter);
}
