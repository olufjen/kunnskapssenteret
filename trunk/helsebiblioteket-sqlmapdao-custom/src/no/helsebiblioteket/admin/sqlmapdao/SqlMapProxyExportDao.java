package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.ProxyExportDao;
import no.helsebiblioteket.admin.domain.export.ProxyResult;
import no.helsebiblioteket.admin.domain.line.ProxyHitLine;
import no.helsebiblioteket.admin.domain.parameter.ProxyExportParameter;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

public class SqlMapProxyExportDao extends IbatisSqlMapClientDaoSupport implements ProxyExportDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<ProxyResult> getProxyExportList(ProxyExportParameter parameter) {
		return (List<ProxyResult>) getSqlMapClientTemplate().queryForList("getProxyExportList", parameter);
	}
	public void insertHitsList(ProxyHitLine line){
		getSqlMapClientTemplate().insert("insertProxyHit", line);
	}
}
