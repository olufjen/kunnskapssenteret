package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.ProxyExportDao;
import no.helsebiblioteket.admin.domain.export.ProxyResult;
import no.helsebiblioteket.admin.domain.line.ProxyHitLine;
import no.helsebiblioteket.admin.domain.parameter.ProxyExportParameter;

public class SqlMapProxyExportDao extends SqlMapClientDaoSupport implements ProxyExportDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<ProxyResult> getProxyExportList(ProxyExportParameter parameter) {
		return (List<ProxyResult>) getSqlMapClientTemplate().queryForList("getProxyExportList", parameter);
	}
	public void insertHitsList(ProxyHitLine line){
		getSqlMapClientTemplate().insert("insertProxyHit", line);
	}
}
