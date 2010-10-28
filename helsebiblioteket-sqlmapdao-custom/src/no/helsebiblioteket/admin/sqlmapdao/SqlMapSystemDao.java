package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.SystemDao;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

public class SqlMapSystemDao extends IbatisSqlMapClientDaoSupport implements SystemDao {
	public System getSystemByKey(SystemKey systemKey) {
		return (System) getSqlMapClientTemplate().queryForObject("getSystemByKey", systemKey);
	}
}
