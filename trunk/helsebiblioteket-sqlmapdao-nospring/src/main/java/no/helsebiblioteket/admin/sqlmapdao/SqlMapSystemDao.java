package no.helsebiblioteket.admin.sqlmapdao;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.SystemDao;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.SystemKey;

public class SqlMapSystemDao extends IbatisSqlMapClientDaoSupport implements SystemDao {
	public System getSystemByKey(SystemKey systemKey) {
		return (System) getSqlMapClientTemplate().queryForObject("getSystemByKey", systemKey);
	}
}
