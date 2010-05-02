package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.line.ProxyConfigLine;

public interface ProxyConfigDao {
	// Edit

	// Fetch
	public List<ProxyConfigLine> getProxyConfigListAll();
}
